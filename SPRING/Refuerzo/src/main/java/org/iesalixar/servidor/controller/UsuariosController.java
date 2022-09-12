package org.iesalixar.servidor.controller;

import java.util.List;

import org.iesalixar.servidor.dto.AlumnoDTO;
import org.iesalixar.servidor.dto.ProfesorDTO;
import org.iesalixar.servidor.dto.UsuarioDTO;
import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Departamento;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.model.Usuario;
import org.iesalixar.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuariosController {

	@Autowired
	UsuarioServiceImpl usuarioService;

	@RequestMapping("/usuarios")
	public String usuarios(Model model) {

		List<Usuario> usuario = usuarioService.getAllUsuarios();

		model.addAttribute("usuario", usuario);
		return "usuario/usuarios";
	}

	@GetMapping("/usuarios/edit")
	public String editUsuarioGet(@RequestParam(name = "user") String user, Model model) {

		Usuario usuario = usuarioService.findUsuarioByIdModel(Long.parseLong(user));
		model.addAttribute("usuario", usuario);

		return "usuario/editUsuario";
	}

	@PostMapping("/usuarios/edit")
	public String updateUsuarioPost(@ModelAttribute Usuario usu) {

		if (usuarioService.actualizarUsuario(usu) == null) {
			return "redirect:/usuarios/edit?error=error&user" + usu.getId();
		}
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/delete")
	public String eliminarUsuario(@RequestParam(required = true, name = "user") String user, Model model) {

		Usuario usuario = usuarioService.findUsuarioByIdModel(Long.parseLong(user));

		if (usuario != null) {
			usuarioService.eliminarUsuario(usuario);
			return "redirect:/usuarios?codigo=" + user;
		} else {
			return "redirect:/usuarios/";
		}
	}

	@GetMapping("/usuarios/addUsuario")
	public String addUsuarioGet(@RequestParam(required = false, name = "error") String error, Model model) {

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		model.addAttribute("usuarioDTO", usuarioDTO);
		model.addAttribute("error", error);

		return "usuario/addUsuario";
	}

	@PostMapping("/usuarios/addUsuario")
	public String addUsuarioPost(@ModelAttribute UsuarioDTO usuarioDTO) {

		Usuario usuario = new Usuario();
		usuario.setActivo(usuarioDTO.isActivo());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellidos(usuarioDTO.getApellidos());
		usuario.setUserName(usuarioDTO.getUsuario());
		usuario.setRole(usuarioDTO.getRole());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setPassword(new BCryptPasswordEncoder(15).encode(usuarioDTO.getPassword()));

		if (usuarioService.insertUsuario(usuario) == null) {
			return "redirect:/usuarios/addUsuario?error=Existe&Usuario=" + usuarioDTO.getUsuario();
		}

		return "redirect:/usuarios";

	}

}
