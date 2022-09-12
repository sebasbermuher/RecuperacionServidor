package org.iesalixar.servidor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.dto.GradoAsignaturaDTO;
import org.iesalixar.servidor.dto.GradoDTO;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.model.Usuario;
import org.iesalixar.servidor.services.AsignaturaServiceImpl;
import org.iesalixar.servidor.services.GradoServiceImpl;
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
public class GradoController {

	@Autowired
	GradoServiceImpl gradoService;

	@Autowired
	ProfesorServiceImpl profesorService;

	@Autowired
	AsignaturaServiceImpl asignaturaService;

	@RequestMapping("/grados")
	public String grados(Model model) {

		List<Grado> grados = gradoService.getAllGrado();

		model.addAttribute("grados", grados);
		return "grados/grados";
	}

	@GetMapping("/grados/addGrado")
	public String addGradoGet(@RequestParam(required = false, name = "error") String error, Model model) {

		GradoDTO gradoDTO = new GradoDTO();

		model.addAttribute("gradoDTO", gradoDTO);
		model.addAttribute("error", error);

		return "grados/addGrado";
	}

	@PostMapping("/grados/addGrado")
	public String addGradoPost(@ModelAttribute GradoDTO gradoDTO, Model model) {

		Grado grado = new Grado();

		grado.setNombre(gradoDTO.getNombre());

		if (gradoService.insertarGrado(grado) == null) {
			return "redirect:/grados/addGrado?error=Existe&Grado=" + gradoDTO.getNombre();
		}
		return "redirect:/grados";
	}

	@GetMapping("/grados/asignaturas")
	public String asignaturasGrado(@RequestParam(required = false, name = "codigo") String codigo,
			@RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			return "redirect:/grados/";
		}
		Optional<Grado> grado = gradoService.findGradoByIdOptional(Long.parseLong(codigo));
		model.addAttribute("grado", grado.get());

		List<Profesor> profesor = profesorService.getAllProfesores();
		List<Grado> mostrarGrados = gradoService.getAllGrado();
		model.addAttribute("profesor", profesor);
		model.addAttribute("mostrarGrados", mostrarGrados);

		if (grado.get().getAsignaturas().size() == 0 || grado == null) {
			error = "error";
			model.addAttribute("error", error);
		}
		return "grados/gradoAsignaturas";
	}

	@GetMapping("/grados/edit")
	public String editGradosGet(@RequestParam(name = "grad") String grad, Model model) {

		Grado grado = gradoService.findGradoById(Long.parseLong(grad));
		model.addAttribute("grado", grado);

		return "grados/editGrados";
	}

	@PostMapping("/grados/edit")
	public String editGradosPost(@ModelAttribute Grado gra) {

		if (gradoService.actualizarGrado(gra) == null) {
			return "redirect:/grados/edit?error=error&grad" + gra.getId();
		}
		return "redirect:/grados";
	}

	@GetMapping("/grados/asignaturas/delete")
	public String borrarAsignaturaGrado(@RequestParam(required = false, name = "asig") String asig, Model model) {

		Asignatura asigEntity = asignaturaService.findAsignaturaByIdOptional(Long.parseLong(asig)).get();
		Long gradoId = asigEntity.getGrado().getId();

		asigEntity.setGrado(null);

		asignaturaService.actualizarAsignatura(asigEntity);

		return "redirect:/grados/asignaturas?codigo=" + gradoId;
	}

	@GetMapping("/grados/addAsigGrado")
	public String addAsigGradoGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "id_grad") String id_grad, Model model) {
		GradoAsignaturaDTO asigGrado = new GradoAsignaturaDTO();
		List<Grado> grados = gradoService.getAllGrado();

		// Si son todas las asignaturas independientemente de si es null cambiar esta
		// lista por "asignaturas".
//		List<Asignatura> asignaturasPrev = asignaturaService.getAllAsignaturas();
		List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();

		// Si son todas las asignaturas independientemente de si es null, comentar de la
		// lista hasta el final del for
//		List<Asignatura> asignaturas = new ArrayList<>();
//		
//		for (int i = 0; i < asignaturasPrev.size(); i++) {
//			Asignatura asignatura = asignaturasPrev.get(i);
//			if (asignatura.getGrado() != null || asignatura.getGrado() == null) {
//				asignaturas.add(asignatura);
//			}
//		}

		model.addAttribute("asigGrado", asigGrado);
		model.addAttribute("asignaturas", asignaturas);
		model.addAttribute("grados", grados);
		model.addAttribute("error", error);

		if (id_grad != null) {
			model.addAttribute("id_grad", id_grad);
		}
		return "grados/addAsigGrado";
	}

	@PostMapping("/grados/addAsigGrado")
	public String addAsigGradoPost(@ModelAttribute GradoAsignaturaDTO asigGrado, Model model) {

		Grado grado = gradoService.findGradoById(asigGrado.getId_grado());
		Asignatura asig = asignaturaService.findAsignaturaByIdOptional(asigGrado.getId_asignatura()).get();

		if (grado != null && asig != null) {

			asig.setGrado(grado);
			asignaturaService.actualizarAsignatura(asig);

			return "redirect:/grados/asignaturas?codigo=" + grado.getId();

		} else {
			return "redirect:/grados/addAsigGrado/";
		}
	}

	@GetMapping("/grados/delete")
	public String eliminarGrado(@RequestParam(required = true, name = "grado") String grado, Model model) {

		Grado grad = gradoService.findGradoById(Long.parseLong(grado));

		if (grad != null) {
			gradoService.eliminarGrado(grad);
			return "redirect:/grados?codigo=" + grado;
		} else {
			return "redirect:/grados/";
		}
	}

}
