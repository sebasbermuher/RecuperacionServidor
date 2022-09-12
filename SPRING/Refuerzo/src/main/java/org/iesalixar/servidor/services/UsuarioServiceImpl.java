package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.model.Usuario;
import org.iesalixar.servidor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository userRepo;

	@Override
	public Usuario insertUsuario(Usuario usuario) {

//		if (usuario != null) {
//
//			return userRepo.save(usuario);
//		}
//
//		return null;
		if (usuario != null && getUsuarioByUserName(usuario.getUserName()) == null
				&& getUsuarioByEmail(usuario.getEmail()) == null) {
			Usuario user = userRepo.save(usuario);
			return user;
		}

		return null;

	}

	@Override
	public List<Usuario> getAllUsuarios() {
		List<Usuario> usuario = userRepo.findAll();
		if (usuario != null && usuario.size() > 0) {
			return usuario;
		}
		return new ArrayList<Usuario>();
	}

	@Override
	public Usuario findUsuarioByIdModel(Long id) {
		if (id != null) {
			return userRepo.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		if (usuario == null || usuario.getUserName() == null || usuario.getEmail() == null) {
			return null;
		}
		return userRepo.save(usuario);
	}

	@Override
	public Usuario eliminarUsuario(Usuario usuario) {
		if (usuario != null) {
			userRepo.delete(usuario);
		}
		return null;
	}

	@Override
	public Usuario getUsuarioByUserName(String username) {
		if (username != null) {
			Usuario user = userRepo.findByUserName(username);
			return user;
		}
		return null;
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		if (email != null) {
			Usuario user = userRepo.findByEmail(email);
			return user;
		}
		return null;
	}

}
