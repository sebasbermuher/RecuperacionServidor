package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Departamento;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.model.Usuario;

public interface UsuarioService {

	public Usuario insertUsuario(Usuario usuario);
	public List<Usuario> getAllUsuarios();
	
	public Usuario findUsuarioByUserName(String userName);
	
	public Usuario eliminarUsuario(Usuario usuario);
	public Usuario findUsuarioById(Long id);


		
}
