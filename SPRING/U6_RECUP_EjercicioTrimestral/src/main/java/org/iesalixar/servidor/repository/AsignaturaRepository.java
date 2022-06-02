package org.iesalixar.servidor.repository;

import java.util.Optional;

import org.iesalixar.servidor.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura,Long> {
	
	public Asignatura findByNombre(String nombre);
	public Optional<Asignatura> findById(Long id);

}
