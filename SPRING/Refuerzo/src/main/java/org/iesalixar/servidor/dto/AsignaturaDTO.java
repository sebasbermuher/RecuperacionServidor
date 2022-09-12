package org.iesalixar.servidor.dto;

import java.io.Serializable;

import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Profesor;

public class AsignaturaDTO implements Serializable {
	private String nombre;
	private Double creditos;
	private String tipo;
	private int curso;
	private int cuatrimestre;
	private Profesor id_profesor;
	private Grado id_grado;

	public AsignaturaDTO() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCreditos() {
		return creditos;
	}

	public void setCreditos(Double creditos) {
		this.creditos = creditos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	public int getCuatrimestre() {
		return cuatrimestre;
	}

	public void setCuatrimestre(int cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	public Profesor getId_profesor() {
		return id_profesor;
	}

	public void setId_profesor(Profesor id_profesor) {
		this.id_profesor = id_profesor;
	}

	public Grado getId_grado() {
		return id_grado;
	}

	public void setId_grado(Grado id_grado) {
		this.id_grado = id_grado;
	}

}