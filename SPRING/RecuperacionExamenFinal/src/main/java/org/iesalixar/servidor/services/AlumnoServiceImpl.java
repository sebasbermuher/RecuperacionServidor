package org.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Profesor;
import org.iesalixar.servidor.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	AlumnoRepository alumnoRepo;

	@Override
	public Optional<Alumno> findAlumnoById(Long id) {

		return alumnoRepo.findById(id);
	}

	@Override
	public List<Alumno> getAllAlumnos() {

		return alumnoRepo.findAll();
	}

	@Override
	public Alumno getAlumnoByNif(String nif) {
		if (nif != null) {
			Alumno alum = alumnoRepo.findByNif(nif);
			return alum;
		}
		return null;
	}

	@Override
	public Alumno insertarAlumno(Alumno alumno) {
		if (alumno != null && getAlumnoByNif(alumno.getNif()) == null) {
			Alumno alum = alumnoRepo.save(alumno);
			return alum;
		}

		return null;
	}

	@Override
	public Alumno actualizarAlumno(Alumno alumno) {

		if (alumno == null || alumno.getId() == null) {
			return null;
		}
		return alumnoRepo.save(alumno);
	}

	@Override
	public Alumno findAlumnoByIdModel(Long id) {
		if (id != null) {
			return alumnoRepo.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public Alumno eliminarAlumno(Alumno alumno) {
		if (alumno != null) {
			alumnoRepo.delete(alumno);
		}

		return null;
	}

}
