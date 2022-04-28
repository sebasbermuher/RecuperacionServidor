package org.iesalixar.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iesalixar.servidor.bd.PoolDB;
import org.iesalixar.servidor.model.OrderDetail;
import org.iesalixar.servidor.model.Productos;

public class DAOOrderDetailsImpl implements DAOOrderDetails {

	@Override
	public ArrayList<OrderDetail> getDetailsFromOrder(int orderNumber) {
		
		ArrayList<OrderDetail> orderdetails = new ArrayList<OrderDetail>();
		OrderDetail od = null;
		Productos product = null;
		Connection con = null;

		try {

			String sql = "select * from orderdetails where  orderNumber = ?";
			PoolDB pool = new PoolDB();
			con = pool.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, orderNumber);
			

			ResultSet rs = statement.executeQuery();

			// Como el campo de búsqueda es la clave solo debería obtener un resultado
			// si no es así estaremos machacando cada vez el valor de customer y
			while (rs.next()) {

				
				od = new OrderDetail();
				od.setOrderLineNumber(rs.getInt("orderLineNumber"));
				od.setPriceEach(rs.getDouble("priceEach"));
				od.setQuantityOrdered(rs.getInt("quantityOrdered"));
				
				String productCode = rs.getString("productCode");
				
				DAOProductImpl daoProduct = new DAOProductImpl();
				
				
				product =daoProduct.getProductByCode(productCode);
				
				od.setProducts(product);
				
				orderdetails.add(od);
				

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

		return orderdetails;

	}

}
