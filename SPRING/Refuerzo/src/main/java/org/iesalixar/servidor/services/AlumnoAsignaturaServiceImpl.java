package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.AlumnoAsignatura;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.repository.AlumnoAsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoAsignaturaServiceImpl implements AlumnoAsignaturaService {

	@Autowired
	AlumnoAsignaturaRepository alumnoAsignaturaRepo;

	@Override
	public List<AlumnoAsignatura> getAllAlumnoAsignatura() {
		List<AlumnoAsignatura> alumnoAsignaturaBD = alumnoAsignaturaRepo.findAll();

		if (alumnoAsignaturaBD != null && alumnoAsignaturaBD.size() > 0) {
			return alumnoAsignaturaBD;
		}

		return new ArrayList<AlumnoAsignatura>();
	}

	@Override
	public AlumnoAsignatura insertarAlumnoAsignatura(AlumnoAsignatura alumnoAsignatura) {
		if (alumnoAsignatura != null) {
			AlumnoAsignatura alumAsig = alumnoAsignaturaRepo.save(alumnoAsignatura);
			return alumAsig;
		}

		return null;
	}

	@Override
	public AlumnoAsignatura findAlumnoAsignaturaById(Alumno id, Asignatura id1) {
		if (id != null && id1 != null) {

			AlumnoAsignatura alumAsig = (AlumnoAsignatura) alumnoAsignaturaRepo.findByAlumnoAndAsignatura(id, id1);

			return alumAsig;
		}

		return null;
	}

	@Override
	public AlumnoAsignatura actualizarAlumnoAsignaturas(AlumnoAsignatura alumnoAsignatura) {
		if (alumnoAsignatura == null || alumnoAsignatura.getAlumno() == null
				|| alumnoAsignatura.getAsignatura() == null) {
			return null;
		}
		return alumnoAsignaturaRepo.save(alumnoAsignatura);
	}

	@Override
	public void deleteAlumnoAsignaturaById(AlumnoAsignatura alumnoAsignatura) {
		alumnoAsignaturaRepo.delete(alumnoAsignatura);
	}

}
