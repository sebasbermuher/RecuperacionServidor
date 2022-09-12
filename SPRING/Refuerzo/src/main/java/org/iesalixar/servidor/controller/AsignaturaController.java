package org.iesalixar.servidor.controller;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.dto.AlumnoAsignaturaDTO;
import org.iesalixar.servidor.dto.AsignaturaDTO;
import org.iesalixar.servidor.dto.ProfesorAsignaturaDTO;
import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.services.AlumnoService;
import org.iesalixar.servidor.services.AsignaturaService;
import org.iesalixar.servidor.services.GradoService;
import org.iesalixar.servidor.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AsignaturaController {

	@Autowired
	AsignaturaService asignaturaService;

	@Autowired
	ProfesorService profesorService;

	@Autowired
	GradoService gradoService;

	@Autowired
	AlumnoService alumnoService;

	@RequestMapping("/asignaturas")
	public String asignaturas(Model model) {

		List<Asignatura> asignatura = asignaturaService.getAllAsignaturas();

		model.addAttribute("asignatura", asignatura);
		return "asignatura/asignatura";
	}

	@GetMapping("/asignaturas/addAsignatura")
	public String addAsignaturaGet(@RequestParam(required = false, name = "error") String error, Model model) {

		AsignaturaDTO asignaturaDTO = new AsignaturaDTO();
		List<Profesor> profesor = profesorService.getAllProfesores();
		List<Grado> grado = gradoService.getAllGrado();

		model.addAttribute("asignaturaDTO", asignaturaDTO);
		model.addAttribute("profesor", profesor);
		model.addAttribute("grado", grado);
		model.addAttribute("error", error);

		return "asignatura/addAsignatura";
	}

	@PostMapping("/asignaturas/addAsignatura")
	public String addAsignaturaPost(@ModelAttribute AsignaturaDTO asignaturaDTO, Model model) {

		Asignatura asignatura = new Asignatura();

		asignatura.setNombre(asignaturaDTO.getNombre());
		asignatura.setCreditos(asignaturaDTO.getCreditos());
		asignatura.setTipo(asignaturaDTO.getTipo());
		asignatura.setCurso(asignaturaDTO.getCurso());
		asignatura.setCuatrimestre(asignaturaDTO.getCuatrimestre());
		asignatura.setProfesor(asignaturaDTO.getId_profesor());
		asignatura.setGrado(asignaturaDTO.getId_grado());

		if (asignaturaService.insertarAsignatura(asignatura) == null) {
			return "redirect:/asignaturas/addAsignatura?error=Existe&Asignatura=" + asignaturaDTO.getNombre();
		}
		return "redirect:/asignaturas";
	}

	@GetMapping("/asignaturas/edit")
	public String editAsignaturaGet(@RequestParam(name = "asig") String asig, Model model) {

		Asignatura asignatura = asignaturaService.findAsignaturaById(Long.parseLong(asig));
		List<Profesor> profesor = profesorService.getAllProfesores();
		List<Grado> grado = gradoService.getAllGrado();

		model.addAttribute("asignatura", asignatura);
		model.addAttribute("profesor", profesor);
		model.addAttribute("grado", grado);

		return "asignatura/editAsignatura";
	}

	@PostMapping("/asignaturas/edit")
	public String editAsignaturaPost(@ModelAttribute Asignatura asi) {

		if (asignaturaService.actualizarAsignatura(asi) == null) {
			return "redirect:/asignaturas/edit?error=error&asig" + asi.getId();
		}
		return "redirect:/asignaturas";
	}

	@GetMapping("/asignaturas/alumnos")
	public String asignaturaAlumno(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			return "redirect:/asignaturas/";
		}
		Optional<Asignatura> asignatura = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(codigo));
		model.addAttribute("asignatura", asignatura.get());

		if (asignatura.get().getAlumnosAsignatura().size() == 0 || asignatura == null) {
			error = "error";
			model.addAttribute("error", error);
		}
		return "asignatura/asignaturasAlumnos";
	}

	@GetMapping("/asignaturas/alumnos/delete")
	public String asignaturaAlumnoDelete(@RequestParam(required = true, name = "asig") String asig,
			@RequestParam(required = true, name = "alumn") String alumn) {

		Asignatura asignatura = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(asig)).get();
		if (asignatura != null) {
			Alumno alumno = alumnoService.findAlumnoById(Long.parseLong(alumn)).get();
			asignatura.removeNota(alumno);
			asignaturaService.actualizarAsignatura(asignatura);
			return "redirect:/asignaturas/alumnos?codigo=" + asig;
		} else {
			return "redirect:/asignaturas/";
		}
	}

	@GetMapping("/asignaturas/profesores")
	public String asignaturaProfesores(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			return "redirect:/asignaturas/";
		}

		Optional<Asignatura> asignatura = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(codigo));
		model.addAttribute("asignatura", asignatura.get());

		if (asignatura.get().getProfesor() == null) {
			error = "error";
			model.addAttribute("error", error);
		}

		return "asignatura/asignaturasProfesores";
	}

	@GetMapping("/asignaturas/delete")
	public String eliminarAsignaturas(@RequestParam(required = true, name = "asignatura") String asignatura,
			Model model) {

		Asignatura asig = asignaturaService.findAsignaturaById(Long.parseLong(asignatura));

		if (asig != null) {
			asignaturaService.eliminarAsignatura(asig);
			return "redirect:/asignaturas?codigo=" + asignatura;
		} else {
			return "redirect:/asignaturas/";
		}
	}

//	SE ELIMINA PERO NO REDIRECCIONA BIEN
	@GetMapping("/asignaturas/profesores/delete")
	public String borrarProfesorAsignatura(@RequestParam(required = false, name = "profe") String profe,
			@RequestParam(required = false, name = "asig") String asig, Model model) {

		Asignatura asigEntity = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(asig)).get();
		Long profId = asigEntity.getProfesor().getId();
		asigEntity.setProfesor(null);
		asignaturaService.actualizarAsignatura(asigEntity);

		Profesor profesor = profesorService.findProfesorByIdOptional(Long.parseLong(profe)).get();
		Long asigId = profesor.getAsignatura().getId();
		profesor.setAsignatura(null);

		profesorService.actualizarProfesor(profesor);

		return "redirect:/asignaturas/profesores?codigo=" + asigId;

	}

//	---------------------------------------------------------------------
//	NO FUNCIONA
//	@GetMapping("/asignaturas/asignarAlumno")
//	public String asignarAlumnoAsignaturaGet(@RequestParam(required = false, name = "error") String error,
//			Model model) {
//		AlumnoAsignaturaDTO alumAsig = new AlumnoAsignaturaDTO();
//
//		List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
//		List<Alumno> alumno = alumnoService.getAllAlumnos();
//
//		model.addAttribute("alumAsig", alumAsig);
//		model.addAttribute("asignaturas", asignaturas);
//		model.addAttribute("alumno", alumno);
//		model.addAttribute("error", error);
//		return "asignatura/asignarAlumno";
//	}
//
//	@PostMapping("/asignaturas/asignarAlumno")
//	public String asignarAlumnoAsignaturaPost(@ModelAttribute AlumnoAsignaturaDTO alumAsig, Model model) {
//
//		Alumno alum = alumnoService.findAlumnoByIdModel(alumAsig.getId_alumno());
//
//		Asignatura asig = asignaturaService.findAsignaturaById(alumAsig.getId_asignatura());
//
//		if (alum != null && asig != null) {
//
////			asig.setAlumnosAsignatura(alum);
//			asignaturaService.actualizarAsignatura(asig);
//
//			return "redirect:/asignaturas/alumnos?codigo=" + asig.getId();
//
//		} else {
//			return "redirect:/asignaturas/asignarAlumno/";
//		}
//
//	}
//	------------------------------------------------------------------------

}
