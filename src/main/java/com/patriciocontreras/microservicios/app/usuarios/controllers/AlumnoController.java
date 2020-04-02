package com.patriciocontreras.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patriciocontreras.microservicios.app.usuarios.models.entity.Alumno;
import com.patriciocontreras.microservicios.app.usuarios.service.AlumnoService;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoService service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		// pasamos al cuerpo de la respuesta una lista de entity por eso es un responseentity
		// tipo iterable
		return ResponseEntity.ok().body(service.findAll());
	}
	// si el id despues del @PathVariable se llamara diferente en pathVariable deberia agregar el name al que hace referencia
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get()); // el get retorna el tipo0 alumno
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno){
		Alumno alumnoDB = service.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDB);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno,@PathVariable Long id){
		// se busca el alumno en la bd por id
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDB = o.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
