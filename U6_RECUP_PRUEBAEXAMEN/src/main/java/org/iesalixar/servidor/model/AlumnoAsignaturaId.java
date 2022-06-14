package org.iesalixar.servidor.model;

import java.io.Serializable;
import java.util.Objects;

public class AlumnoAsignaturaId implements Serializable {

	private long alumno;
	private long asignatura;

	public AlumnoAsignaturaId() {
		// TODO Auto-generated constructor stub
	}

	public long getAlumno() {
		return alumno;
	}

	public void setAlumno(long alumno) {
		this.alumno = alumno;
	}

	public long getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(long asignatura) {
		this.asignatura = asignatura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno, asignatura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlumnoAsignaturaId other = (AlumnoAsignaturaId) obj;
		return alumno == other.alumno && asignatura == other.asignatura;
	}

}
