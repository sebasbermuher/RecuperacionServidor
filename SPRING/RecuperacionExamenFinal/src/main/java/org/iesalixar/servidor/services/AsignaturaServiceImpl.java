package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Departamento;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

	@Autowired
	AsignaturaRepository asignaturaRepo;

	@Override
	public List<Asignatura> getAllAsignaturas() {

		// Obtengo el resultado a través del repositorio
		List<Asignatura> asignBD = asignaturaRepo.findAll();

		// Verificando que he obtenido algo
		if (asignBD != null && asignBD.size() > 0) {

			return asignBD;
		}

		// No he obtenido nada devuelvo una lista vacía (para no devolver nulo)
		return new ArrayList<Asignatura>();
	}

	@Override
	public Asignatura findAsignaturaById(Long id) {
		Optional<Asignatura> asignatura = null;

		if (id != null) {
			return asignaturaRepo.findById(id).get();
		} else {
			return null;
		}

	}

	@Override
	public Asignatura actualizarAsignatura(Asignatura asignatura) {
		if (asignatura == null || asignatura.getId() == null || asignatura.getNombre() == null) {
			return null;
		}

		return asignaturaRepo.save(asignatura);
	}

	@Override
	public Asignatura getAsignaturaByNombre(String nombre) {
		if (nombre != null) {

			Asignatura asignatura = asignaturaRepo.findByNombre(nombre);

			return asignatura;
		}
		return null;
	}

	@Override
	public Asignatura insertarAsignatura(Asignatura asignatura) {
		if (asignatura != null && getAsignaturaByNombre(asignatura.getNombre()) == null) {
			Asignatura asig = asignaturaRepo.save(asignatura);
			return asig;
		}

		return null;
	}

	@Override
	public Optional<Asignatura> findAsignaturaByIdOptional(Long id) {
		return asignaturaRepo.findById(id);
	}

	@Override
	public Asignatura eliminarAsignatura(Asignatura asignatura) {
		if (asignatura != null) {
			asignaturaRepo.delete(asignatura);
		}

		return null;
	}

}
