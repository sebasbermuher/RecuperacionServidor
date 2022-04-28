package org.iesalixar.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.bd.PoolDB;
import org.iesalixar.servidor.model.Productos;

public class DAOProductImpl implements DAOProduct {

	@Override
	public ArrayList<Productos> getProduct(String categoria) {
		ArrayList<Productos> productsList = new ArrayList<>();
		Productos product;
		Connection con = null;

		try {

			String sql = "select * from products where productLine=?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, categoria);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				product = new Productos();

				product.setProductCode(rs.getString("productCode"));
				product.setProductName(rs.getString("productName"));
				product.setProductLine(rs.getString("productLine"));
				product.setProductScale(rs.getNString("productScale"));
				product.setProductVendor(rs.getString("productVendor"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setQuantityInStock(rs.getInt("quantityInStock"));
				product.setBuyPrice(rs.getDouble("buyPrice"));
				product.setMsrp(rs.getDouble("MSRP"));

				productsList.add(product);

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

		return productsList;

	}

	@Override
	public Productos getProducts(String productCode) {
		Productos product = null;
		Connection con = null;

		try {

			String sql = "select * from products where productCode=?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, productCode);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				product = new Productos();

				product.setProductName(rs.getString("productName"));
				product.setProductCode(rs.getString("productCode"));
				product.setProductLine(rs.getString("productLine"));
				product.setProductScale(rs.getNString("productScale"));
				product.setProductVendor(rs.getString("productVendor"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setQuantityInStock(rs.getInt("quantityInStock"));
				product.setBuyPrice(rs.getDouble("buyPrice"));
				product.setMsrp(rs.getDouble("MSRP"));

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

		return product;
	}

	@Override
	public List<Productos> getAllProducts() {

		ArrayList<Productos> productList = new ArrayList<>();
		Productos product;
		Connection con = null;

		try {

			String sql = "select * from products";

			PoolDB pool = new PoolDB();
			con = pool.getConnection();

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				product = new Productos();

				product.setProductName(rs.getString("productName"));
				product.setProductCode(rs.getString("productCode"));
				product.setProductLine(rs.getString("productLine"));
				product.setProductScale(rs.getNString("productScale"));
				product.setProductVendor(rs.getString("productVendor"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setQuantityInStock(rs.getInt("quantityInStock"));
				product.setBuyPrice(rs.getDouble("buyPrice"));
				product.setMsrp(rs.getDouble("MSRP"));

				productList.add(product);

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return productList;
	}

	@Override
	public int getDetallesNumPedidos(String productCode) {

		Connection con = null;
		int total = 0;

		try {

			String sql = "select count(*) from orderdetails where productCode= ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productCode);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
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

		return total;
	}

	@Override
	public int getDetallesNumProductasVentas(String productCode) {

		Connection con = null;
		int total = 0;

		try {

			String sql = "select SUM(quantityOrdered) from orderdetails where productCode= ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productCode);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
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

		return total;
	}

	@Override
	public double getDetallesNumVentas(String productCode) {

		Connection con = null;
		double total = 0;

		try {

			String sql = "select (sum(quantityOrdered)*priceEach) from orderdetails where productCode= ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productCode);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				total = rs.getDouble(1);
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

		return total;
	}

	@Override
	public boolean updateProduct(Productos product) {
		int resultado = 0;
		Connection con = null;

		try {

			String sql = "update products set  productName=?, productLine=?, productScale=?, productVendor=?, productDescription=?, quantityInStock=?, buyPrice=?, msrp=?  where productCode = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, product.getProductName());
			statement.setString(2, product.getProductLine());
			statement.setString(3, product.getProductScale());
			statement.setString(4, product.getProductVendor());
			statement.setString(5, product.getProductDescription());
			statement.setInt(6, product.getQuantityInStock());
			statement.setDouble(7, product.getBuyPrice());
			statement.setDouble(8, product.getMsrp());
			statement.setString(9, product.getProductCode());

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
	public Productos getProductByCode(String code) {
		Productos product = null;
		Connection con = null;

		try {

			String sql = "select * from products where  productCode=?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, code);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				product = new Productos();
				product.setProductName(rs.getString("productName"));
				product.setProductCode(rs.getString("productCode"));
				product.setProductLine(rs.getString("productLine"));
				product.setProductScale(rs.getString("productScale"));
				product.setProductVendor(rs.getString("productVendor"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setQuantityInStock(rs.getInt("quantityInStock"));
				product.setBuyPrice(rs.getDouble("buyPrice"));
				product.setMsrp(rs.getDouble("MSRP"));

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

		return product;
	}

	@Override
	public boolean insertProduct(Productos product) {
		int resultado = 0;
		Connection con = null;

		try {

			String sql = "insert into products values(?,?,?,?,?,?,?,?,?)";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, product.getProductCode());
			statement.setString(2, product.getProductName());
			statement.setString(3, "");
			statement.setString(4, product.getProductScale());
			statement.setString(5, product.getProductVendor());
			statement.setString(6, product.getProductDescription());
			statement.setInt(7, product.getQuantityInStock());
			statement.setDouble(8, product.getBuyPrice());
			statement.setDouble(9, product.getMsrp());
			

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