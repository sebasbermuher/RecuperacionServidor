package org.iesalixar.servidor.services;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.AlumnoAsignatura;
import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Departamento;

public interface AlumnoAsignaturaService {
	
	public AlumnoAsignatura findAlumnoAsignaturaById(Alumno id, Asignatura id1);
	
	public AlumnoAsignatura actualizarAlumnoAsignatura(AlumnoAsignatura alumAsig);


}
