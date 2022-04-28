package org.iesalixar.servidor.dao;

import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.model.ProductLine;

public interface DAOProductLine {

	public ProductLine getProductLine(String productLine);

	public ArrayList<ProductLine> getAllProductLine();

	public boolean updateProductLine(ProductLine productLine);

	public boolean removeProductLine(String productLine);

	public boolean createProductLine(ProductLine productLine);
	
	public List<ProductLine> getCategorias();
}
