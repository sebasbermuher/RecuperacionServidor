package org.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.model.Alumno;

public interface AlumnoService {

	public List<Alumno> getAllAlumnos();
	public Optional<Alumno> findAlumnoById(Long id);
}
