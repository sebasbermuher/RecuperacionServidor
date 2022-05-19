package org.iesalixar.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.bd.PoolDB;
import org.iesalixar.servidor.model.Customer;
import org.iesalixar.servidor.model.Order;
import org.iesalixar.servidor.model.OrderDetail;
import org.iesalixar.servidor.model.Productos;

public interface DAOProduct {
	public ArrayList<Productos> getProduct(String categoria);
	public Productos getProducts(String productCode);
	public List<Productos> getAllProducts();
	public int getDetallesNumPedidos(String productCode);
	public int getDetallesNumProductasVentas(String productCode);
	public double getDetallesNumVentas(String productCode);
	public boolean updateProduct(Productos product); 
	public Productos getProductByCode(String code);
	public boolean insertProduct(Productos product);
//	public Productos getProduct2(int productLine) ;
	public boolean updateProductProductLine(String producto, String productLine2);
	public boolean actualizarProducto(String productLine,String productName);

	
}