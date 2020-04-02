package com.patriciocontreras.microservicios.app.usuarios.service;

import java.util.Optional;

import com.patriciocontreras.microservicios.app.usuarios.models.entity.Alumno;

public interface AlumnoService {
	
	public Iterable<Alumno> findAll();
	
	// Optional sirve para preguntar si viene o no el objeto Alumnop
	public Optional<Alumno> findById(Long id);

	public Alumno save(Alumno alumno);
	
	public void deleteById(Long id);
}
