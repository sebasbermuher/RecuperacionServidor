package org.iesalixar.servidor.dao;

import java.util.List;

import org.iesalixar.servidor.model.Oficinas;

public interface DAOOficina {
	
	public List<Oficinas> getAllOficinas();
	public Oficinas getOffice(String officeCode);
	public Oficinas getOficinas(String city);

}
