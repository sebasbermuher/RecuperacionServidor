package org.iesalixar.servidor.repository;

import org.iesalixar.servidor.model.Alumno;
import org.iesalixar.servidor.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
	public Alumno findByNif(String nif);

}
