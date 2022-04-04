package org.iesalixar.servidor.dao;

import org.iesalixar.servidor.model.Usuario;

public interface DAOUsuario {

	public Usuario getUsuario(String usuario);
	public boolean registerUsuario(Usuario usuario);
	
	
	
}
