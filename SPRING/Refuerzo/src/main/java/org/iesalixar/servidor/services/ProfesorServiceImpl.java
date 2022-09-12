package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {

	@Autowired
	ProfesorRepository profesorRepo;

	@Override
	public List<Profesor> getAllProfesores() {
		// Obtengo el resultado a través del repositorio
		List<Profesor> profesorBD = profesorRepo.findAll();

		// Verificando que he obtenido algo
		if (profesorBD != null && profesorBD.size() > 0) {

			return profesorBD;
		}

		// No he obtenido nada devuelvo una lista vacía (para no devolver nulo)
		return new ArrayList<Profesor>();
	}

//	@Override
//	public Optional<Profesor> findProfesorById(Long id) {
//		Optional<Profesor> profesor = null;
//		
//		if (id!=null) {
//			profesor = profesorRepo.findById(id);
//		}
//		
//		return profesor;
//	}

	@Override
	public Profesor findProfesorById(Long id) {
		if (id != null) {
			return profesorRepo.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public Profesor insertarProfesor(Profesor profesor) {
		if (profesor != null && getProfesorByNif(profesor.getNif()) == null) {
			Profesor prof = profesorRepo.save(profesor);
			return prof;
		}

		return null;
	}

	@Override
	public Profesor getProfesorByNif(String nif) {
		if (nif != null) {
			Profesor prof = profesorRepo.findByNif(nif);
			return prof;
		}
		return null;

	}

	@Override
	public Optional<Profesor> findProfesorByIdOptional(Long id) {
		return profesorRepo.findById(id);
	}

	@Override
	public Profesor actualizarProfesor(Profesor profesor) {
		if (profesor == null || profesor.getId() == null || profesor.getNombre() == null) {
			return null;
		}

		return profesorRepo.save(profesor);
	}

	@Override
	public Profesor eliminarProfesor(Profesor profesor) {
		if (profesor != null) {
			profesorRepo.delete(profesor);
		}

		return null;
	}

}
