package org.iesalixar.servidor.controller;

import java.util.List;

import org.iesalixar.servidor.dto.AlumnoAsignaturaDTO;
import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.AlumnoAsignatura;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Usuario;
import org.iesalixar.servidor.services.AlumnoAsignaturaServiceImpl;
import org.iesalixar.servidor.services.AlumnoServiceImpl;
import org.iesalixar.servidor.services.AsignaturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MatriculaController {

	@Autowired
	AlumnoAsignaturaServiceImpl alumnoAsignaturaService;

	@Autowired
	AlumnoServiceImpl alumnoService;

	@Autowired
	AsignaturaServiceImpl asignaturaService;

	@RequestMapping("/matriculas")
	public String matriculas(Model model) {

		List<AlumnoAsignatura> alumnoAsignatura = alumnoAsignaturaService.getAllAlumnoAsignatura();

		model.addAttribute("alumnoAsignatura", alumnoAsignatura);
		return "matricula/matriculas";
	}

	@GetMapping("/matriculas/addMatricula")
	public String addMatriculaGet(@RequestParam(required = false, name = "error") String error, Model model) {

		AlumnoAsignaturaDTO alumAsigDTO = new AlumnoAsignaturaDTO();
		List<Alumno> alumno = alumnoService.getAllAlumnos();
		List<Asignatura> asignatura = asignaturaService.getAllAsignaturas();

		model.addAttribute("alumAsigDTO", alumAsigDTO);
		model.addAttribute("alumno", alumno);
		model.addAttribute("asignatura", asignatura);
		model.addAttribute("error", error);

		return "matricula/addMatricula";
	}

	@PostMapping("/matriculas/addMatricula")
	public String addMatriculaPost(@ModelAttribute AlumnoAsignaturaDTO alumAsigDTO, Model model) {

		AlumnoAsignatura alumAsig = new AlumnoAsignatura();

		alumAsig.setAlumno(alumAsigDTO.getId_alumno());
		alumAsig.setAsignatura(alumAsigDTO.getId_asignatura());
		alumAsig.setNota(alumAsigDTO.getNota());

		if (alumnoAsignaturaService.insertarAlumnoAsignatura(alumAsig) == null) {
			return "redirect:/matriculas/addMatricula?error=Existe&Matricula=" + alumAsigDTO.getId_alumno();

		}
		return "redirect:/matriculas";
	}

	@GetMapping("/matriculas/delete")
	public String eliminarMatriculas(@RequestParam(required = true, name = "asignatura") Asignatura asignatura,
			@RequestParam(required = true, name = "alumno") Alumno alumno, Model model) {

		AlumnoAsignatura alumAsig = alumnoAsignaturaService.findAlumnoAsignaturaById(alumno, asignatura);

		if (alumAsig != null) {
			alumnoAsignaturaService.deleteAlumnoAsignaturaById(alumAsig);
//			return "redirect:/matriculas?codigo=" + alumAsig;
			return "redirect:/matriculas";

		} else {
			return "redirect:/matriculas/";
		}
	}
}
