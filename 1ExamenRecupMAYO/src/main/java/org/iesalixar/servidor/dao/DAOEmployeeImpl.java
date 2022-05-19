package org.iesalixar.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.bd.PoolDB;
import org.iesalixar.servidor.model.Employee;

public class DAOEmployeeImpl implements DAOEmployee {

	@Override
	public Employee getEmployee(int employeeNumber) {
		Employee employee = new Employee();
		Connection con = null;

		try {

			String sql = "select * from employees where employeeNumber = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, employeeNumber);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				employee = new Employee();

				employee.setEmployeeNumber(rs.getInt("employeeNumber"));
				employee.setLastName(rs.getString("lastName"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setExtension(rs.getString("extension"));
				employee.setEmail(rs.getString("email"));
				employee.setOfficeCode(rs.getString("officeCode"));
				employee.setReportsTo(rs.getInt("reportsTo"));
				employee.setJobTitle(rs.getString("jobTitle"));

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return employee;

	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> employeeList = new ArrayList<>();

		Connection con = null;

		try {

			String sql = "select * from employees";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeNumber(rs.getInt("employeeNumber"));
				employee.setLastName(rs.getString("lastName"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setExtension(rs.getString("extension"));
				employee.setEmail(rs.getString("email"));
				employee.setOfficeCode(rs.getString("officeCode"));
				employee.setReportsTo(rs.getInt("reportsTo"));
				employee.setJobTitle(rs.getString("jobTitle"));

				employeeList.add(employee);

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return employeeList;

	}

	@Override
	public boolean insertEmployee(Employee employee) {
		int resultado = 0;
		Connection con = null;

		try {

			String sql = "insert into employees values(?,?,?,?,?,?,?,?)";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, employee.getEmployeeNumber());
			statement.setString(2, employee.getLastName());
			statement.setString(3, employee.getFirstName());
			statement.setString(4, employee.getExtension());
			statement.setString(5, employee.getEmail());
			statement.setString(6, employee.getOfficeCode());
			statement.setInt(7, employee.getReportsTo());
			statement.setString(8, employee.getJobTitle());

			resultado = statement.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return (resultado == 0 ? false : true);
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		int resultado = 0;
		Connection con = null;

		try {

			String sql = "update employees set  lastName=?, firstName=?, extension=?, email=?, officeCode=?, reportsTo=?, jobTitle=?  where employeeNumber = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, employee.getLastName());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getExtension());
			statement.setString(4, employee.getEmail());
			statement.setString(5, employee.getOfficeCode());
			statement.setInt(6, employee.getReportsTo());
			statement.setString(7, employee.getJobTitle());
			statement.setInt(8, employee.getEmployeeNumber());

			resultado = statement.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return (resultado == 0 ? false : true);

	}

	@Override
	public boolean removeEmployee(int employeeNumber) {
		int resultado = 0;
		Connection con = null;

		try {

			String sql = "delete from employees where employeeNumber=?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, employeeNumber);

			resultado = statement.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return (resultado == 0 ? false : true);

	}

	@Override
	public int getNumberEmployee() {

		int codEmployee = 0;
		Connection con = null;

		try {

			// String sql = "select * from employees where employeeNumber = (select
			// max(employeeNumber) from employees)";
			String sql = "select max(employeeNumber) from employees";

			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// codEmployee=rs.getInt("employeeNumber");
				codEmployee = rs.getInt("max(employeeNumber)");
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return codEmployee + 1;
	}

	@Override
	public Employee getDatosNombre(String firstName) {
		Employee employee = new Employee();
		Connection con = null;

		try {

			String sql = "select * from employees where firstName = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, firstName);
			ResultSet rs = statement.executeQuery();

			// Como el campo de búsqueda es la clave solo debería obtener un resultado
			// si no es así estaremos machacando cada vez el valor de productLine
			while (rs.next()) {

				employee = new Employee();

				employee.setEmployeeNumber(rs.getInt("employeeNumber"));
				employee.setLastName(rs.getString("lastName"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setExtension(rs.getString("extension"));
				employee.setEmail(rs.getString("email"));
				employee.setOfficeCode(rs.getString("officeCode"));
				employee.setReportsTo(rs.getInt("reportsTo"));
				employee.setJobTitle(rs.getString("jobTitle"));

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return employee;

	}
}
