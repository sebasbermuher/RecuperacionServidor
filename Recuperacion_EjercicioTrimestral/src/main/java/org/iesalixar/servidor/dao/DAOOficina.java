package org.iesalixar.servidor.dao;

import java.util.ArrayList;

import org.iesalixar.servidor.model.Oficinas;

public interface DAOOficina {
	
	public ArrayList<Oficinas> getAllOficinas();
	public Oficinas getOffice(String officeCode);
	public Oficinas getOficinas(String city);

}
