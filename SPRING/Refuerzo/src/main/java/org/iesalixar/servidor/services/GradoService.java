package org.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.model.Departamento;
import org.iesalixar.servidor.model.Grado;
import org.iesalixar.servidor.model.Usuario;

public interface GradoService {
	
	public List<Grado> getAllGrado();
	public Grado insertarGrado(Grado grado);
	public Grado getGradoByNombre(String nombre);
	
	public Grado findGradoById(Long id);
	
	public Optional<Grado> findGradoByIdOptional(Long id);
	public Grado actualizarGrado(Grado grado);
	public Grado eliminarGrado(Grado grado);

	
}
