package org.iesalixar.servidor.dto;

public class ProfesorAsignaturaDTO {

	private Long id_asignatura;
	private Long id_profesor;

	public ProfesorAsignaturaDTO() {

	}

	public Long getId_asignatura() {
		return id_asignatura;
	}

	public void setId_asignatura(Long id_asignatura) {
		this.id_asignatura = id_asignatura;
	}

	public Long getId_profesor() {
		return id_profesor;
	}

	public void setId_profesor(Long id_profesor) {
		this.id_profesor = id_profesor;
	}

}
