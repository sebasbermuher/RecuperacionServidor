package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.AlumnoAsignatura;
import org.iesalixar.servidor.model.Asignatura;

public interface AlumnoAsignaturaService {
	
	public List<AlumnoAsignatura> getAllAlumnoAsignatura();
	public AlumnoAsignatura insertarAlumnoAsignatura(AlumnoAsignatura alumnoAsignatura);
	
	public AlumnoAsignatura findAlumnoAsignaturaById(Alumno id, Asignatura id1);
	public AlumnoAsignatura actualizarAlumnoAsignaturas(AlumnoAsignatura alumnoAsignatura);

	public void deleteAlumnoAsignaturaById(AlumnoAsignatura alumnoAsignatura);
	
}
