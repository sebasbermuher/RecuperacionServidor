package org.iesalixar.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.bd.PoolDB;
import org.iesalixar.servidor.model.Oficinas;

public class DAOOficinaImpl implements DAOOficina {

	@Override
	public ArrayList<Oficinas> getAllOficinas() {
		ArrayList<Oficinas> oficinasList = new ArrayList<>();
		Oficinas oficina = null;
		Connection con = null;

		try {

			String sql = "select * from offices";

			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				oficina = new Oficinas();

				oficina.setOfficeCode(rs.getString("officeCode"));
				oficina.setCity(rs.getString("city"));
				oficina.setPhone(rs.getString("phone"));
				oficina.setAddressLine1(rs.getString("addressLine1"));
				oficina.setAddressLine2(rs.getString("addressLine2"));
				oficina.setState(rs.getString("state"));
				oficina.setCountry(rs.getString("country"));
				oficina.setPostalCode(rs.getString("postalCode"));
				oficina.setTerritory(rs.getString("territory"));

				oficinasList.add(oficina);

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return oficinasList;
	}

	@Override
	public Oficinas getOffice(String officeCode) {

		Oficinas oficina = null;

		Connection con = null;

		try {

			String sql = "select * from offices where officeCode= ?";

			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, officeCode);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				oficina = new Oficinas();
				oficina.setOfficeCode(rs.getString("officeCode"));
				oficina.setCity(rs.getString("city"));
				oficina.setPhone(rs.getString("phone"));
				oficina.setAddressLine1(rs.getString("addressLine1"));
				oficina.setAddressLine2(rs.getString("addressLine2"));
				oficina.setState(rs.getString("state"));
				oficina.setCountry(rs.getString("country"));
				oficina.setPostalCode(rs.getString("postalCode"));
				oficina.setTerritory(rs.getString("territory"));

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return oficina;
	}

	@Override
	public Oficinas getOficinas(String city) {
		Oficinas oficina = null;

		Connection con = null;

		try {

			String sql = "select * from offices where city= ?";

			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, city);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				oficina = new Oficinas();
				oficina.setOfficeCode(rs.getString("officeCode"));
				oficina.setCity(rs.getString("city"));
				oficina.setPhone(rs.getString("phone"));
				oficina.setAddressLine1(rs.getString("addressLine1"));
				oficina.setAddressLine2(rs.getString("addressLine2"));
				oficina.setState(rs.getString("state"));
				oficina.setCountry(rs.getString("country"));
				oficina.setPostalCode(rs.getString("postalCode"));
				oficina.setTerritory(rs.getString("territory"));

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return oficina;
	}


}