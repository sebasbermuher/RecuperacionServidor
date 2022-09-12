package org.iesalixar.servidor.dto;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Asignatura;

public class AlumnoAsignaturaDTO {

	private Asignatura id_asignatura;
	private Alumno id_alumno;
	private int nota;

	public AlumnoAsignaturaDTO() {

	}

	public Asignatura getId_asignatura() {
		return id_asignatura;
	}

	public void setId_asignatura(Asignatura id_asignatura) {
		this.id_asignatura = id_asignatura;
	}

	public Alumno getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(Alumno id_alumno) {
		this.id_alumno = id_alumno;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

}
