package org.iesalixar.servidor.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.dto.AlumnoAsignaturaNotaDTO;
import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.AlumnoAsignatura;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Usuario;
import org.iesalixar.servidor.services.AlumnoAsignaturaService;
import org.iesalixar.servidor.services.AlumnoAsignaturaServiceImpl;
import org.iesalixar.servidor.services.AlumnoServiceImpl;
import org.iesalixar.servidor.services.AsignaturaServiceImpl;
import org.iesalixar.servidor.services.GradoServiceImpl;
import org.iesalixar.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	GradoServiceImpl gradosService;

	@Autowired
	UsuarioServiceImpl usuariosService;

	@Autowired
	AlumnoAsignaturaServiceImpl alumAsigService;

	@Autowired
	AlumnoServiceImpl alumnoService;

	@Autowired
	AsignaturaServiceImpl asignaturaService;

	@Autowired
	AlumnoAsignaturaServiceImpl alumnoAsignaturaService;

	@RequestMapping("/")
	public String grados(Model model) {

		List<Grado> grado = gradosService.getAllGrado();

		model.addAttribute("grado", grado);
		return "index";
	}

	@RequestMapping("/grados/estudiantes")
	public String mostrarGradosEstudiantes(@RequestParam(required = false, name = "codigo") String idGrado,
			@RequestParam(required = false, name = "error") String error, Model model) {

		Optional<Grado> grados = gradosService.OptionalFindGradoById(Long.parseLong(idGrado));

		if (grados.isPresent()) {
			model.addAttribute("grados", grados);
			model.addAttribute("asignaturas", grados.get().getAsignaturas());
			model.addAttribute("nombreGrado", grados.get().getNombre());
		}

		if (grados.get().getAsignaturas().size() == 0 || grados == null) {
			error = "error";
			model.addAttribute("error", error);
		}

		return "estudiantesGrados";
	}

	@GetMapping("/grados/estudiantes/editarnota")
	public String editarNotaGet(@RequestParam(required = false, name = "alum") String alum,
			@RequestParam(required = false, name = "asig") String asig, Model model) {
		if (alum == null || asig == null) {
			return "redirect:/grados/estudiantes/";
		}
		Optional<Alumno> alumno = alumnoService.findAlumnoById(Long.parseLong(alum));
		Optional<Asignatura> asignatura = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(asig));

		model.addAttribute("alumno", alumno.get());
		model.addAttribute("asignatura", asignatura.get());

		for (AlumnoAsignatura alumAsig : alumno.get().getAlumnoAsignaturas()) {
			if (alumAsig.getAsignatura().getId() == asignatura.get().getId()) {
				model.addAttribute("alumAsig", alumAsig);
			}
		}

		return "editarNota";
	}

	@PostMapping("/grados/estudiantes/editarnota")
	public String editarNotaPost(@ModelAttribute AlumnoAsignaturaNotaDTO alumAsig, Model model) throws ParseException {

		AlumnoAsignatura alumnoAsignaturaBD = new AlumnoAsignatura();
		Optional<Alumno> alumno = alumnoService.findAlumnoById(alumAsig.getAlumno());
		Optional<Asignatura> asignatura = asignaturaService.findAsignaturaByIdOptional(alumAsig.getAsignatura());

		alumnoAsignaturaBD.setAlumno(alumno.get());
		alumnoAsignaturaBD.setAsignatura(asignatura.get());
		alumnoAsignaturaBD.setNota(alumAsig.getNota());

		alumnoAsignaturaService.actualizarAlumnoAsignatura(alumnoAsignaturaBD);

		return "redirect:/grados/estudiantes?codigo=" + asignatura.get().getGrado().getId();
	}

	@GetMapping("/usuarios")
	public String usuarios(Model model) {

		List<Usuario> usuarios = usuariosService.getAllUsuarios();

		model.addAttribute("usuarios", usuarios);
		return "usuarios";
	}

	@GetMapping("/usuarios/delete")
	public String eliminarUsuario(@RequestParam(required = true, name = "user") String user, Model model) {

		Usuario usuario = usuariosService.findUsuarioById(Long.parseLong(user));

		if (usuario != null) {
			usuariosService.eliminarUsuario(usuario);
			return "redirect:/usuarios?codigo=" + user;
		} else {
			return "redirect:/usuarios/";
		}
	}

}
