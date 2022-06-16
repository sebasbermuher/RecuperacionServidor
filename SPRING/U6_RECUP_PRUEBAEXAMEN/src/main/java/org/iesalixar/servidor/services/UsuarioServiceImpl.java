package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Grado;
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

		if (usuario != null) {

			return userRepo.save(usuario);
		}

		return null;

	}

	@Override
	public List<Usuario> getAllUsuarios() {
		List<Usuario> usuarioBD = userRepo.findAll();

		if (usuarioBD != null && usuarioBD.size() > 0) {
			return usuarioBD;
		}

		return new ArrayList<Usuario>();
	}

	@Override
	public Usuario findUsuarioByUserName(String userName) {
		if (userName != null) {
			return userRepo.findByUserName(userName);
		} else {
			return null;
		}
	}

	@Override
	public Usuario eliminarUsuario(Usuario usuario) {
		if (usuario != null) {
			userRepo.delete(usuario);
		}

		return null;
	}

	@Override
	public Usuario findUsuarioById(Long id) {
		if (id != null) {
			return userRepo.findById(id).get();
		} else {
			return null;
		}

	}

	

}
