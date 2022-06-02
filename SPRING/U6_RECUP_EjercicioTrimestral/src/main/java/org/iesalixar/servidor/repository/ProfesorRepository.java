package org.iesalixar.servidor.repository;

import java.util.Optional;

import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
	public Profesor findByNif(String nif);
}
