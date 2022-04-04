package org.iesalixar.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.iesalixar.servidor.bd.PoolDB;
import org.iesalixar.servidor.model.Usuario;

public class DAOUsuarioImpl implements DAOUsuario {

	public DAOUsuarioImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usuario getUsuario(String usuario) {
		Usuario user = null;
		Connection con = null;

		try {
			String sql = "select * from usuarios where usuario=?";
			PoolDB pool = new PoolDB();

			con = pool.getConnection();

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, usuario);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				user = new Usuario();
				user.setUsuario(rs.getString("usuario"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
			}

			con.close();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return user;
	}

	@Override
	public boolean registerUsuario(Usuario usuario) {
		int resultado = 0;
		Connection con = null;

		try {

			String sql = "insert into usuarios values(?,?,?,?,?,?)";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getPassword());
			statement.setString(4, "user");
			statement.setString(5, usuario.getFirstName());
			statement.setString(6, usuario.getLastName());

			resultado = statement.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return (resultado == 0 ? false : true);
	}

}
