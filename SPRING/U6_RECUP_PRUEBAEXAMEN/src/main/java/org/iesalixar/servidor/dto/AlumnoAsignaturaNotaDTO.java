package org.iesalixar.servidor.dto;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Asignatura;

public class AlumnoAsignaturaNotaDTO {
	private Long alumno;
	private Long asignatura;
	private int nota;
	
	public AlumnoAsignaturaNotaDTO() {
	}

	public Long getAlumno() {
		return alumno;
	}

	public void setAlumno(Long alumno) {
		this.alumno = alumno;
	}

	public Long getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Long asignatura) {
		this.asignatura = asignatura;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	
}
