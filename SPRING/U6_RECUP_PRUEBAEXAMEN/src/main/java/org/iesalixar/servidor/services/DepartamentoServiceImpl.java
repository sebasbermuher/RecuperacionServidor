package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.model.Departamento;
import org.iesalixar.servidor.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	DepartamentoRepository departamentoRepo;

	@Override
	public List<Departamento> getAllDepartments() {		
		
		
		//Obtengo el resultado a través del repositorio
		List<Departamento> deptsBD = departamentoRepo.findAll();		
		
		
		//Verificando que he obtenido algo 
		if (deptsBD!= null && deptsBD.size()> 0) {
			
			return deptsBD;
		}
		
		//No he obtenido nada devuelvo una lista vacía (para no devolver nulo)
		return new ArrayList<Departamento>(); 
		
	}

	@Override
	public Departamento getDepartamentByName(String nombre) {
		
		if (nombre!=null) {
		
			Departamento dpto = departamentoRepo.findByNombre(nombre);
			
			return dpto; 			
		}
		
		return null;
	}

	@Override
	public Departamento insertarDepartamento(Departamento departamento) {
		
		if (departamento!=null && getDepartamentByName(departamento.getNombre())==null) {
			Departamento dpto = departamentoRepo.save(departamento);
			return dpto;
		}
		
		return null;
	}
	
	
}
