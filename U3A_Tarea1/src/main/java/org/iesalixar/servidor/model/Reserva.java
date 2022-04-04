package org.iesalixar.servidor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Reserva implements Serializable {

	private String fInicio, fFin;
	private int numPer;
	private String[] servicios;

	public Reserva() {
		// TODO Auto-generated constructor stub
	}

	public String getfInicio() {
		return fInicio;
	}

	public void setfInicio(String fInicio) {
		this.fInicio = fInicio;
	}

	public String getfFin() {
		return fFin;
	}

	public void setfFin(String fFin) {
		this.fFin = fFin;
	}

	

	public String[] getServicios() {
		return servicios;
	}

	public void setServicios(String[] servicios) {
		this.servicios = servicios;
	}

	public int getNumPer() {
		return numPer;
	}

	public void setNumPer(int numPer) {
		this.numPer = numPer;
	}

	@Override
	public String toString() {
		return "Reserva [fInicio=" + fInicio + ", fFin=" + fFin + ", numPer=" + numPer + ", servicios="
				+ Arrays.toString(servicios) + "]";
	}

	
	
	
	
	

}