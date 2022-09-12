package org.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.model.Asignatura;
import org.iesalixar.servidor.model.Departamento;

public interface DepartamentoService {
	
	public List<Departamento> getAllDepartments();
	public Departamento getDepartamentByName(String nombre);
	public Departamento insertarDepartamento(Departamento departamento);
	
	public Optional<Departamento> findDepartamentoByIdOptional(Long id);
	public Departamento actualizarDepartamento(Departamento departamento);
	public Departamento findDepartamentoById(Long id);
	public Departamento eliminarDepartamento(Departamento departamento);





}
