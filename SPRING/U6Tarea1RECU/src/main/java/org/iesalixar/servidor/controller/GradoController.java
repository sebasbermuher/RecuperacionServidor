package org.iesalixar.servidor.controller;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.dto.GradoAsignaturaDTO;
import org.iesalixar.servidor.dto.GradoDTO;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.services.AsignaturaServiceImpl;
import org.iesalixar.servidor.services.GradoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradoController {

	@Autowired
	GradoServiceImpl gradoService;

	@Autowired
	AsignaturaServiceImpl asignaturaService;

	@RequestMapping("/grados")
	public String grado(Model model) {

		List<Grado> grado = gradoService.getAllGrado();

		model.addAttribute("contenido", "grados");
		model.addAttribute("grado", grado);
		return "grado";
	}

	@GetMapping("/grados/asignaturas")
	public String asignaturasGrados(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			return "redirect:/grados/";
		}
		
//		Para el erros en caso de que un grado no tenga asignatura.
		Grado grado = gradoService.findGradoById(Long.parseLong(codigo));
		model.addAttribute("grado",grado);		
		
		if(grado.getAsignaturas().size() == 0 || grado == null) {
			error="error";
			model.addAttribute("error",error);	
		}
		return "asignaturasGrados";
		
		
	}

	@GetMapping("/grados/addGrado")
	public String addGradoGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "grado") String nombre, Model model) {

		GradoDTO gradodto = new GradoDTO();

		model.addAttribute("gradodto", gradodto);
		model.addAttribute("error", error);
		model.addAttribute("previo", nombre);
		return "addGrado";
	}

	@PostMapping("/grados/addGrado")
	public String addGradoPost(@ModelAttribute GradoDTO gradoDTO, Model model) {

		Grado gradoBD = new Grado();
		gradoBD.setNombre(gradoDTO.getNombre());

		if (gradoService.insertarGrado(gradoBD) == null) {
			return "redirect:/grados/addGrado?error=Existe&grado=" + gradoDTO.getNombre();
		}

		return "redirect:/grados";
	}

	@GetMapping("/grados/addasignatura")
	public String addasignaturaGet(@RequestParam(required = false, name = "error") String error, Model model) {

		GradoAsignaturaDTO asigGrado = new GradoAsignaturaDTO();

		List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
		List<Grado> grados = gradoService.getAllGrado();

		model.addAttribute("asigGrado", asigGrado);
		model.addAttribute("asignaturas", asignaturas);
		model.addAttribute("grados", grados);
		model.addAttribute("error", error);
		return "addasignatura";
	}

	@PostMapping("/grados/addasignatura")
	public String addasignaturaPost(@ModelAttribute GradoAsignaturaDTO asigGrado, Model model) {

		Grado grado = gradoService.findGradoById(asigGrado.getId_grado());

		Asignatura asig = asignaturaService.findAsignaturaById(asigGrado.getId_asignatura());

		if (grado != null && asig != null) {

			asig.setGrado(grado);
			asignaturaService.actualizarAsignatura(asig);

			return "redirect:/grados/asignaturas?codigo=" + grado.getId();

		} else {
			return "redirect:/grados/addasignatura/";
		}

	}

}
