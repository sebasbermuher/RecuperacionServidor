package org.iesalixar.servidor.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.dto.AlumnoAsignaturaDTO;
import org.iesalixar.servidor.dto.AlumnoDTO;
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
public class AlumnoController {

	@Autowired
	AlumnoServiceImpl alumnoService;

	@Autowired
	AsignaturaServiceImpl asignaturaService;

	@Autowired
	AlumnoAsignaturaServiceImpl alumnoAsignaturaService;

	AlumnoAsignatura alumnoAsignatura1 = new AlumnoAsignatura();

	@RequestMapping("/alumno")
	public String profesores(Model model) {

		List<Alumno> alumno = alumnoService.getAllAlumnos();

		model.addAttribute("alumno", alumno);
		return "alumno/alumno";
	}

	@GetMapping("/alumno/addAlumno")
	public String addAlumnoGet(@RequestParam(required = false, name = "error") String error, Model model) {

		AlumnoDTO alumnoDTO = new AlumnoDTO();

		model.addAttribute("alumnoDTO", alumnoDTO);
		model.addAttribute("error", error);

		return "alumno/addAlumno";
	}

	@PostMapping("/alumno/addAlumno")
	public String addAlumnoPost(@ModelAttribute AlumnoDTO alumnoDTO, Model model) {

		Alumno alumno = new Alumno();

		alumno.setNif(alumnoDTO.getNif());
		alumno.setNombre(alumnoDTO.getNombre());
		alumno.setApellido1(alumnoDTO.getApellido1());
		alumno.setApellido2(alumnoDTO.getApellido2());
		alumno.setCiudad(alumnoDTO.getCiudad());
		alumno.setDireccion(alumnoDTO.getDireccion());
		alumno.setTelefono(alumnoDTO.getTelefono());
		alumno.setFechaNacimiento(alumnoDTO.getFechaNacimiento());
		alumno.setSexo(alumnoDTO.getSexo());

		if (alumnoService.insertarAlumno(alumno) == null) {
			return "redirect:/alumno/addAlumno?error=Existe&Alumno=" + alumnoDTO.getNif() + alumnoDTO.getNombre();
		}

		return "redirect:/alumno";
	}

	@GetMapping("/alumno/asignaturas")
	public String asignaturaAlumno(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			return "redirect:/alumno/";
		}

		Optional<Alumno> alumno = alumnoService.findAlumnoById(Long.parseLong(codigo));
		model.addAttribute("alumno", alumno.get());

		if (alumno.get().getAlumnoAsignaturas().size() == 0 || alumno == null) {
			error = "error";
			model.addAttribute("error", error);
		}

		return "alumno/alumnosAsignaturas";
	}

	@GetMapping("/alumno/asignaturas/delete")
	public String asignaturaAlumnoDelete(@RequestParam(required = true, name = "asig") String asig,
			@RequestParam(required = true, name = "alumn") String alumn) {

		Asignatura asignatura = asignaturaService.findAsignaturaById(Long.parseLong(asig));

		if (asignatura != null) {
			Alumno alumno = alumnoService.findAlumnoById(Long.parseLong(alumn)).get();
			asignatura.removeNota(alumno);
			asignaturaService.actualizarAsignatura(asignatura);
			return "redirect:/alumno/asignaturas?codigo=" + alumn;
		} else {
			return "redirect:/alumno/";
		}
	}

	@GetMapping("/alumno/edit")
	public String editAlumnGet(@RequestParam(name = "alumn") String alumn, Model model) {

		Alumno alumno = alumnoService.findAlumnoByIdModel(Long.parseLong(alumn));
		model.addAttribute("alumno", alumno);

		return "alumno/editAlumno";
	}

	@PostMapping("/alumno/edit")
	public String updateAlumnoPost(@ModelAttribute Alumno alu) {

		if (alumnoService.actualizarAlumno(alu) == null) {
			return "redirect:/alumno/edit?error=error&alumn" + alu.getId();
		}
		return "redirect:/alumno";
	}

	@GetMapping("/alumno/delete")
	public String eliminarAlumno(@RequestParam(required = true, name = "alumno") String alumno, Model model) {

		Alumno alum = alumnoService.findAlumnoByIdModel(Long.parseLong(alumno));

		if (alum != null) {
			alumnoService.eliminarAlumno(alum);
			return "redirect:/alumno?codigo=" + alumno;
		} else {
			return "redirect:/alumno/";
		}
	}

	@RequestMapping("/alumnos/asignaturas/cambiarNota")
	public String alumnoCambiarNota(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "codigoAsignatura") String codigoAsignatura, Model model) {

		Optional<Alumno> alumnos = alumnoService.findAlumnoById(Long.parseLong(codigo));
		Optional<Asignatura> asignaturas = asignaturaService
				.findAsignaturaByIdOptional(Long.parseLong(codigoAsignatura));
		AlumnoAsignatura alumnosAsignaturas = alumnoAsignaturaService.findAlumnoAsignaturaById(alumnos.get(),
				asignaturas.get());

		model.addAttribute("alumnosAsignaturas", alumnosAsignaturas);

		AlumnoAsignaturaDTO alumAsig = new AlumnoAsignaturaDTO();
		alumnoAsignatura1.setAlumno(alumnos.get());
		alumnoAsignatura1.setAsignatura(asignaturas.get());
		model.addAttribute("alumAsig", alumAsig);
		return "alumno/cambiarNota";
	}

	@PostMapping("/alumnos/asignaturas/cambiarNota")
	public String editProfesorPost(@RequestParam(required = false, name = "codigo") String codigo,
			@ModelAttribute AlumnoAsignaturaDTO alumAsig, Model model) throws ParseException {

		AlumnoAsignatura alumnoAsignaturaBD = new AlumnoAsignatura();

		alumnoAsignaturaBD.setAlumno(alumnoAsignatura1.getAlumno());
		alumnoAsignaturaBD.setAsignatura(alumnoAsignatura1.getAsignatura());
		alumnoAsignaturaBD.setNota(alumAsig.getNota());

		alumnoAsignaturaService.actualizarAlumnoAsignaturas(alumnoAsignaturaBD);

		return "redirect:/alumno/asignaturas?codigo=" + alumnoAsignatura1.getAlumno().getId();
	}

}
