package org.iesalixar.servidor.controller;

import java.util.List;

import org.iesalixar.servidor.dto.GradoAsignaturaDTO;
import org.iesalixar.servidor.dto.ProfesorAsignaturaDTO;
import org.iesalixar.servidor.dto.ProfesorDTO;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Departamento;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.services.AsignaturaServiceImpl;
import org.iesalixar.servidor.services.DepartamentoServiceImpl;
import org.iesalixar.servidor.services.ProfesorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfesoresController {
	
	
	@Autowired
	ProfesorServiceImpl profesoresService;
	
	@Autowired
	DepartamentoServiceImpl departamentoService;
	
	@Autowired
	AsignaturaServiceImpl asignaturaService;
	
	@RequestMapping("/profesores")
	public String profesores(Model model) {

		List<Profesor> profesor = profesoresService.getAllProfesores();

		model.addAttribute("contenido", "profesores");
		model.addAttribute("profesor", profesor);
		return "profesores";
	}
	
	
	@GetMapping("/profesores/asignaturas")
	public String asignaturasProfesores(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			return "redirect:/profesores/";
		}
		
//		Para el erros en caso de que un grado no tenga asignatura.
		Profesor profesor = profesoresService.findProfesorById(Long.parseLong(codigo));
		model.addAttribute("profesor",profesor);		
		
		if(profesor.getAsignaturas().size() == 0 || profesor == null) {
			error="error";
			model.addAttribute("error",error);	
		}
		return "asignaturasProfesores";
	}
	
	
	@GetMapping("/profesores/addProfesor")
	public String addProfesorGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "profesor") String nombre, Model model) {

		ProfesorDTO profesorDTO = new ProfesorDTO();
		
		List<Departamento> departamentos = departamentoService.getAllDepartments();
		
		model.addAttribute("profesorDTO", profesorDTO);
		model.addAttribute("error", error);
		model.addAttribute("nombre", nombre);
		model.addAttribute("departamentos", departamentos);

		
		return "addProfesor";
	}
	
	@PostMapping("/profesores/addProfesor")
	public String addProfesorPost(@ModelAttribute ProfesorDTO profesorDTO, Model model) {

		Profesor profesor = new Profesor();
		
		profesor.setNombre(profesorDTO.getNombre());
		profesor.setApellido1(profesorDTO.getApellido1());
		profesor.setApellido2(profesorDTO.getApellido2());
		profesor.setCiudad(profesorDTO.getCiudad());
		profesor.setDireccion(profesorDTO.getDireccion());
		profesor.setFechaNacimiento(profesorDTO.getFechaNacimiento());
		profesor.setNif(profesorDTO.getNif());
		profesor.setSexo(profesorDTO.getSexo());
		profesor.setTelefono(profesorDTO.getTelefono());
		profesor.setDepartamento(profesorDTO.getDepartamento());
		
		
		if (profesoresService.insertarProfesor(profesor) == null) {
			return "redirect:/profesores/addProfesor?error=Existe&Profesor=" + profesorDTO.getNif()+ profesorDTO.getNombre();
		}
		
		return "redirect:/profesores";
	}
	
	
	@GetMapping("/profesores/addAsignatura")
	public String addAsignaturaGet(@RequestParam(required = false, name = "error") String error, Model model) {

		ProfesorAsignaturaDTO profAsig = new ProfesorAsignaturaDTO();

		List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
		List<Profesor> profesor = profesoresService.getAllProfesores();

		model.addAttribute("profAsig", profAsig);
		model.addAttribute("asignaturas", asignaturas);
		model.addAttribute("profesor", profesor);
		model.addAttribute("error", error);
		return "addAsignatura";
	}
	
	@PostMapping("/profesores/addAsignatura")
	public String addAsignaturaPost(@ModelAttribute ProfesorAsignaturaDTO profAsig, Model model) {
	
		Profesor prof = profesoresService.findProfesorById(profAsig.getId_profesor());

		Asignatura asig = asignaturaService.findAsignaturaById(profAsig.getId_asignatura());

		if (prof != null && asig != null) {

			asig.setProfesor(prof);
			asignaturaService.actualizarAsignatura(asig);

			return "redirect:/profesores/asignaturas?codigo=" + prof.getId();

		} else {
			return "redirect:/profesores/addAsignatura/";
		}

	}
	

}
