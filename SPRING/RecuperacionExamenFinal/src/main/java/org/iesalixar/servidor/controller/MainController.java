package org.iesalixar.servidor.controller;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.dto.AlumnoAsignaturaDTO;
import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.AlumnoAsignatura;
import org.iesalixar.servidor.model.Asignatura;
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
public class MainController {

	@Autowired
	AsignaturaServiceImpl asignaturaService;

	@Autowired
	AlumnoServiceImpl alumnoService;

	@Autowired
	AlumnoAsignaturaServiceImpl alumnoAsignaturaService;

	@RequestMapping("/")
	public String asignaturas(Model model) {

		List<Asignatura> asignatura = asignaturaService.getAllAsignaturas();

		model.addAttribute("asignatura", asignatura);
		return "index";
	}

	@GetMapping("/matricula/list")
	public String asignaturaAlumno(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			return "redirect:/";
		}
		Optional<Asignatura> asignatura = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(codigo));
		model.addAttribute("asignatura", asignatura.get());

		if (asignatura.get().getAlumnosAsignatura().size() == 0 || asignatura == null) {
			error = "error";
			model.addAttribute("error", error);
		}
		return "matriculaList";
	}

	@GetMapping("/matricula/list/delete")
	public String asignaturaAlumnoDelete(@RequestParam(required = true, name = "asig") String asig,
			@RequestParam(required = true, name = "alumn") String alumn) {

		Asignatura asignatura = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(asig)).get();
		if (asignatura != null) {
			Alumno alumno = alumnoService.findAlumnoById(Long.parseLong(alumn)).get();
			asignatura.removeNota(alumno);
			asignaturaService.actualizarAsignatura(asignatura);
			return "redirect:/matricula/list?codigo=" + asig;
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/matricula/add")
	public String addMatriculaGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(name = "asig") String asig, Model model) {

		Asignatura asigNATURAS = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(asig)).get();
		model.addAttribute("asigNATURAS", asigNATURAS);

		AlumnoAsignaturaDTO alumAsigDTO = new AlumnoAsignaturaDTO();
		List<Alumno> alumno = alumnoService.getAllAlumnos();
		List<Asignatura> asignatura = asignaturaService.getAllAsignaturas();

		model.addAttribute("alumAsigDTO", alumAsigDTO);
		model.addAttribute("alumno", alumno);
		model.addAttribute("asignatura", asignatura);
		model.addAttribute("error", error);
		return "addMatricula";
	}

	@PostMapping("/matricula/add")
	public String addMatriculaPost(@ModelAttribute AlumnoAsignaturaDTO alumAsigDTO, Model model) {

		AlumnoAsignatura alumAsig = new AlumnoAsignatura();

		alumAsig.setAlumno(alumAsigDTO.getId_alumno());
		alumAsig.setAsignatura(alumAsigDTO.getId_asignatura());
		alumAsig.setNota(alumAsigDTO.getNota());

		if (alumnoAsignaturaService.insertarAlumnoAsignatura(alumAsig) == null) {
			return "redirect:/matricula/add?error=Existe&asig=" + alumAsigDTO.getId_asignatura().getId();
		}
//		return "redirect:/matricula/list?codigo=" + alumAsig.getAsignatura().getId();
		return "redirect:/";
	}

	@GetMapping("/matricula/report")
	public String reportMatriculaGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(name = "asig") String asig, Model model) {

		Asignatura asigNATURAS = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(asig)).get();
		model.addAttribute("asigNATURAS", asigNATURAS);

		return "reportMatricula";
	}

}
