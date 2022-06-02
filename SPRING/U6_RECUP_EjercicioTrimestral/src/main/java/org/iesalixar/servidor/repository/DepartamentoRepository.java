package org.iesalixar.servidor.repository;

import org.iesalixar.servidor.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Long>{
	
	public Departamento findByNombre(String nombre);
}
