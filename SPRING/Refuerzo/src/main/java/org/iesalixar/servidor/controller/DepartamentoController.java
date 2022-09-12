package org.iesalixar.servidor.controller;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.dto.DepartamentoDTO;
import org.iesalixar.servidor.dto.DepartamentoProfesorDTO;
import org.iesalixar.servidor.dto.ProfesorAsignaturaDTO;
import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Departamento;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.services.DepartamentoService;
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
public class DepartamentoController {

	@Autowired
	DepartamentoService departamentoService;

	@Autowired
	ProfesorService profesorService;

	@RequestMapping("/departamentos")
	public String departamentos(Model model) {

		List<Departamento> departamentos = departamentoService.getAllDepartments();

		model.addAttribute("departamentos", departamentos);
		return "departamentos/departamentos";
	}

	@GetMapping("/departamentos/addDepartamento")
	public String addDepartamentoGet(@RequestParam(required = false, name = "error") String error, Model model) {

		DepartamentoDTO departamentoDTO = new DepartamentoDTO();

		model.addAttribute("departamentoDTO", departamentoDTO);
		model.addAttribute("error", error);

		return "departamentos/addDepartamento";
	}

	@PostMapping("/departamentos/addDepartamento")
	public String addDepartamentoPost(@ModelAttribute DepartamentoDTO departamentoDTO, Model model) {

		Departamento departamento = new Departamento();

		departamento.setNombre(departamentoDTO.getNombre());

		if (departamentoService.insertarDepartamento(departamento) == null) {
			return "redirect:/departamentos/addDepartamento?error=Existe&Departamento=" + departamentoDTO.getNombre();
		}
		return "redirect:/departamentos";
	}

	@GetMapping("/departamentos/profesores")
	public String profesoresDepartamento(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			return "redirect:/departamentos/";
		}
		Optional<Departamento> departamento = departamentoService.findDepartamentoByIdOptional(Long.parseLong(codigo));
		model.addAttribute("departamento", departamento.get());

		if (departamento.get().getProfesor().size() == 0 || departamento == null) {
			error = "error";
			model.addAttribute("error", error);
		}
		return "departamentos/departamentosProfesor";
	}

	@GetMapping("/departamentos/profesores/delete")
	public String eliminarProfesorDepartamento(@RequestParam(required = false, name = "prof") String prof,
			Model model) {

		Profesor profesor = profesorService.findProfesorByIdOptional(Long.parseLong(prof)).get();
		Long dptoId = profesor.getDepartamento().getId();

		profesor.setDepartamento(null);

		profesorService.actualizarProfesor(profesor);

		return "redirect:/departamentos/profesores?codigo=" + dptoId;

	}

	@GetMapping("/departamentos/edit")
	public String editDepartamentosGet(@RequestParam(name = "depart") String depart, Model model) {

		Departamento departamento = departamentoService.findDepartamentoById(Long.parseLong(depart));
		model.addAttribute("departamento", departamento);

		return "departamentos/editDepartamentos";
	}

	@PostMapping("/departamentos/edit")
	public String editDepartamentosPost(@ModelAttribute Departamento departam) {

		if (departamentoService.actualizarDepartamento(departam) == null) {
			return "redirect:/departamentos/edit?error=error&depart" + departam.getId();
		}
		return "redirect:/departamentos";
	}

	@GetMapping("/departamentos/asignarProfesor")
	public String asignarProfesorDepartamentoGet(@RequestParam(required = false, name = "error") String error,
			Model model) {

		DepartamentoProfesorDTO deparProf = new DepartamentoProfesorDTO();
		List<Departamento> departamento = departamentoService.getAllDepartments();
		List<Profesor> profesor = profesorService.getAllProfesores();

		model.addAttribute("deparProf", deparProf);
		model.addAttribute("departamento", departamento);
		model.addAttribute("profesor", profesor);
		model.addAttribute("error", error);
		return "departamentos/asignarProfesor";
	}

	@PostMapping("/departamentos/asignarProfesor")
	public String asignarProfesorDepartamentoPost(@ModelAttribute DepartamentoProfesorDTO deparProf, Model model) {

		Profesor prof = profesorService.findProfesorById(deparProf.getId_profesor());

		Departamento depar = departamentoService.findDepartamentoById(deparProf.getId_departamento());

		if (prof != null && depar != null) {

			prof.setDepartamento(depar);
			departamentoService.actualizarDepartamento(depar);

			return "redirect:/departamentos/profesores?codigo=" + depar.getId();

		} else {
			return "redirect:/departamentos/asignarProfesor/";
		}
	}

	@GetMapping("/departamentos/delete")
	public String eliminarDepartamento(@RequestParam(required = true, name = "departamento") String departamento,
			Model model) {

		Departamento depart = departamentoService.findDepartamentoById(Long.parseLong(departamento));

		if (depart != null) {
			departamentoService.eliminarDepartamento(depart);
			return "redirect:/departamentos?codigo=" + departamento;
		} else {
			return "redirect:/departamentos/";
		}
	}

}
