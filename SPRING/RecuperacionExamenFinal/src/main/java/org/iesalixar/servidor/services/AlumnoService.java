package org.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Profesor;

public interface AlumnoService {

	public List<Alumno> getAllAlumnos();
	public Optional<Alumno> findAlumnoById(Long id);
	
	public Alumno getAlumnoByNif(String nif);
	public Alumno insertarAlumno(Alumno alumno);
	public Alumno actualizarAlumno(Alumno alumno);
	public Alumno findAlumnoByIdModel(Long id);
	public Alumno eliminarAlumno(Alumno alumno);




}
