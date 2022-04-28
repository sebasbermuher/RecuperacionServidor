package org.iesalixar.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.bd.PoolDB;
import org.iesalixar.servidor.model.Payment;

public class DAOPaymentImpl implements DAOPayment {

	@Override
	public List<Payment> getAllPayments() {
		ArrayList<Payment> paymentsList = new ArrayList<>();
		Connection con = null;

		try {

			String sql = "select * from payments";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				Payment payment = new Payment();

				payment.setCustomerNumber(rs.getInt("customerNumber"));
				payment.setCheckNumber(rs.getString("checkNumber"));
				payment.setDate(rs.getString("paymentDate"));
				payment.setAmount(rs.getDouble("amount"));

				paymentsList.add(payment);

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

		return paymentsList;
	}

	@Override
	public Payment getPayment(int customerNumber, String checkNumber) {

		Payment payment = null;
		Connection con = null;

		try {

			String sql = "select * from payments where customerNumber= ? and checkNumber= ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, customerNumber);
			statement.setString(2, checkNumber);

			ResultSet rs = statement.executeQuery();

			// Como el campo de búsqueda es la clave solo debería obtener un resultado
			// si no es así estaremos machacando cada vez el valor de customer y
			while (rs.next()) {

				payment = new Payment();

				payment.setCustomerNumber(rs.getInt("customerNumber"));
				payment.setCheckNumber(rs.getString("checkNumber"));
				payment.setDate(rs.getString("paymentDate"));
				payment.setAmount(rs.getDouble("amount"));

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

		return payment;
	}

	@Override
	public boolean removePayment(int customerNumber, String checkNumber) {

		int resultado = 0;
		Connection con = null;

		try {

			String sql = "delete from payments where customerNumber=? and checkNumber=?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, customerNumber);
			statement.setString(2, checkNumber);

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
	public boolean updatePayment(Payment payment) {

		int resultado = 0;
		Connection con = null;

		try {

			String sql = "update payments set paymentDate= ?, amount=? where customerNumber = ? and checkNumber = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, payment.getDate());
			statement.setDouble(2, payment.getAmount());
			statement.setInt(3, payment.getCustomerNumber());
			statement.setString(4, payment.getCheckNumber());

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
	public boolean insertPayment(Payment payment) {
		int resultado = 0;
		Connection con = null;

		try {

			String sql = "insert into payments values(?,?,?,?)";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, payment.getCustomerNumber());
			statement.setString(2, payment.getCheckNumber());
			statement.setString(3, payment.getDate());
			statement.setDouble(4, payment.getAmount());

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

}
