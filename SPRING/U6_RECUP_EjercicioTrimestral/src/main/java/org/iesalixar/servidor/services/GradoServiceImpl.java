package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradoServiceImpl implements GradoService {

	@Autowired
	GradoRepository gradoRepo;

	@Override
	public List<Grado> getAllGrado() {

		List<Grado> gradoBD = gradoRepo.findAll();

		if (gradoBD != null && gradoBD.size() > 0) {
			return gradoBD;
		}

		return new ArrayList<Grado>();

	}

//	@Override
//	public Grado getGradoByID(String id) {
//		if (id != null) {
//
//			Grado grado = gradoRepo.findById(id);
//
//			return grado;
//		}
//		return null;
//
//	}

	@Override
	public Grado insertarGrado(Grado grado) {
		if (grado != null && getGradoByNombre(grado.getNombre()) == null) {
			Grado grad = gradoRepo.save(grado);
			return grad;
		}

		return null;
	}

	@Override
	public Grado getGradoByNombre(String nombre) {
		if (nombre != null) {

			Grado grado = gradoRepo.findByNombre(nombre);

			return grado;
		}
		return null;

	}

	@Override
	public Grado findGradoById(Long id) {
		if(id!=null) {
			return gradoRepo.findById(id).get();
		}else {
			return null;
		}
	}

}
