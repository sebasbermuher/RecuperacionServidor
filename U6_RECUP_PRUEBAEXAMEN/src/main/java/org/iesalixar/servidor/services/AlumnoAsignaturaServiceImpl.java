package org.iesalixar.servidor.services;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.AlumnoAsignatura;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.repository.AlumnoAsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoAsignaturaServiceImpl implements AlumnoAsignaturaService {

	@Autowired
	AlumnoAsignaturaRepository alumAsigRepo;

	@Override
	public AlumnoAsignatura findAlumnoAsignaturaById(Alumno id, Asignatura id1) {
		if (id != null && id1 != null) {

			AlumnoAsignatura alumAsig = (AlumnoAsignatura) alumAsigRepo.findByAlumnoAndAsignatura(id, id1);

			return alumAsig;
		}

		return null;
	}

	@Override
	public AlumnoAsignatura actualizarAlumnoAsignatura(AlumnoAsignatura alumAsig) {
		if (alumAsig==null || alumAsig.getAlumno() ==null || alumAsig.getAsignatura()==null || alumAsig.getNota()==0 ) {
			return null;
		}
		
		return alumAsigRepo.save(alumAsig); 
		
	}

}
