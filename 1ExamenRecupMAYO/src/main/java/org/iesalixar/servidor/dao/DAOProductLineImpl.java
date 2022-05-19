package org.iesalixar.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.bd.PoolDB;
import org.iesalixar.servidor.model.ProductLine;

public class DAOProductLineImpl implements DAOProductLine {

	public DAOProductLineImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ProductLine getProductLine(String productLine) {

		ProductLine categoria = null;
		Connection con = null;

		try {

			String sql = "select * from productlines where productLine = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productLine);
			ResultSet rs = statement.executeQuery();

			// Como el campo de búsqueda es la clave solo debería obtener un resultado
			// si no es así estaremos machacando cada vez el valor de productLine
			while (rs.next()) {

				categoria = new ProductLine();

				categoria.setProductLine(rs.getString("productLine"));
				categoria.setTextDescription(rs.getString("textDescription"));
				categoria.setHtmlDescription(rs.getString("htmlDescription"));

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

		return categoria;

	}

	@Override
	public ArrayList<ProductLine> getAllProductLine() {

		ArrayList<ProductLine> categoriasList = new ArrayList<>();
		ProductLine categoria;
		Connection con = null;

		try {

			String sql = "select * from productlines";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				categoria = new ProductLine();
				categoria.setProductLine(rs.getString("productLine"));
				categoria.setTextDescription(rs.getString("textDescription"));
				categoria.setHtmlDescription(rs.getString("htmlDescription"));

				categoriasList.add(categoria);

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

		return categoriasList;

	}

	@Override
	public boolean updateProductLine(ProductLine productLine) {

		int resultado = 0;
		Connection con = null;

		try {

			String sql = "update productlines set textDescription=?, htmlDescription=? where productLine = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			// statement.setString(1, productLine.getProductLine());
			statement.setString(1, productLine.getTextDescription());
			statement.setString(2, productLine.getHtmlDescription());
			statement.setString(3, productLine.getProductLine());

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
	public boolean removeProductLine(String productLine) {

		int resultado = 0;
		Connection con = null;

		try {

			String sql = "delete from productlines where productLine = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productLine);

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
	public boolean createProductLine(ProductLine productLine) {

		int resultado = 0;
		Connection con = null;

		try {

			String sql = "insert into productlines values(?,?,?,null)";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productLine.getProductLine());
			statement.setString(2, productLine.getTextDescription());
			statement.setString(3, productLine.getHtmlDescription());

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
	public List<ProductLine> getCategorias() {

		ArrayList<ProductLine> categoriasList = new ArrayList<>();
		ProductLine productLines = null;
		Connection con = null;

		try {
			String sql = "select productLine from productlines";
			PoolDB pool = new PoolDB();

			con = pool.getConnection();

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				productLines = new ProductLine();

				productLines.setProductLine(rs.getString("productLine"));

				categoriasList.add(productLines);

			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return categoriasList;
	}

	@Override
	public ProductLine getCategorias2(String productLine) {
		ProductLine pl = new ProductLine();
		Connection con = null;

		try {

			String sql = "select * from productlines where productLine = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productLine);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				pl = new ProductLine();

//				pl.setCustomerNumber(rs.getInt("customerNumber"));
//				pl.setCustomerName(rs.getString("customerName"));
//				pl.setContactLastName(rs.getString("contactLastName"));
//				pl.setContactFirstName(rs.getString("contactFirstName"));
//				customer.setPhone(rs.getString("phone"));
//				customer.setAddressLine1(rs.getString("addressLine1"));
//				customer.setAddressLine2(rs.getString("addressLine2"));
//				customer.setCity(rs.getString("city"));
//				customer.setState(rs.getString("state"));
//				customer.setPostalCode(rs.getString("postalCode"));
//				customer.setCountry(rs.getString("country"));
//				customer.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
//				customer.setCreditLimit(rs.getDouble("creditLimit"));
				
				pl.setProductLine(rs.getString("productLine"));
				pl.setTextDescription(rs.getString("textDescription"));
				pl.setHtmlDescription(rs.getString("htmlDescription"));

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

		return pl;
	}

//	@Override
//	public ArrayList<ProductLine> getDetailsFromProduct(String productLine) {
//		ArrayList<ProductLine> prodLine = new ArrayList<ProductLine>();
//		OrderDetail pl = null;
//		Productos product = null;
//		Connection con = null;
//
//		try {
//
//			String sql = "select * from product where  productLine = ?";
//			PoolDB pool = new PoolDB();
//			con = pool.getConnection();
//			PreparedStatement statement = con.prepareStatement(sql);
//			statement.setString(1, productLine);
//
//			ResultSet rs = statement.executeQuery();
//
//			// Como el campo de búsqueda es la clave solo debería obtener un resultado
//			// si no es así estaremos machacando cada vez el valor de customer y
//			while (rs.next()) {
//
//				pl = new ProductLine();
//				od.setOrderLineNumber(rs.getInt("orderLineNumber"));
//				od.setPriceEach(rs.getDouble("priceEach"));
//				od.setQuantityOrdered(rs.getInt("quantityOrdered"));
//
//				String productCode = rs.getString("productCode");
//
//				DAOProductImpl daoProduct = new DAOProductImpl();
//
//				product = daoProduct.getProductByCode(productCode);
//
//				od.setProduct(product);
//
//				orderdetails.add(od);
//
//			}
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		return orderdetails;
//
//	}

}
