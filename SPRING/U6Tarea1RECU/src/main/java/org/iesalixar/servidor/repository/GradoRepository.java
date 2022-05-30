package org.iesalixar.servidor.repository;

import java.util.Optional;

import org.iesalixar.servidor.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Long> {
	public Grado findByNombre(String nombre);
	public Optional<Grado> findById(Long id);

}
