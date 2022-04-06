package org.iesalixar.servidor.dao;

import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.model.Productos;

public interface DAOProduct {
	public ArrayList<Productos> getProduct(String categoria);
	public Productos getProducts(String productCode);
	public List<Productos> getAllProducts();
	public int getDetallesNumPedidos(String productCode);
	public int getDetallesNumProductasVentas(String productCode);
	public double getDetallesNumVentas(String productCode);
	public boolean updateProduct(Productos product); 
}