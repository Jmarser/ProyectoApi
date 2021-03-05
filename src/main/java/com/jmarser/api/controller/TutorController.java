package com.jmarser.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jmarser.api.entity.Tutor;
import com.jmarser.api.service.TutorService;

@RestController
@RequestMapping("/api")
public class TutorController {

	@Autowired
	private TutorService tutorService;
	
	@GetMapping("/tutores")
	public ResponseEntity<?> getAllTutores(){
		List<Tutor> listado = tutorService.findAll();
		
		if(listado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listado);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tutuores registrados.");
		}
	}
	
	@GetMapping("/tutor/{id}")
	public ResponseEntity<?> getTutorById(@PathVariable(value="id") Long id){
		/*Comprobamos que el id se encuentra en la base de datos*/
		if(tutorService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(tutorService.findById(id));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id no corresponde con ningún tutor.");
		}
	}
	
	@PostMapping("/save_tutor")
	public ResponseEntity<?> saveTutor(@RequestBody Tutor tutor){
		/*comprobamos que el tutor no se encuentre ya en la base de datos*/
		if(tutorService.findByEmail(tutor.getEmail())==null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(tutorService.save(tutor));
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El tutor ya se encuentra en la base de datos.");
		}
	}
	
	@PutMapping("/update_tutor/{id}")
	public ResponseEntity<?> updateTutor(@PathVariable(value="id")Long id, @RequestBody Tutor tutor){
		/*comprobamos que el id corresponda con un tutor*/
		if(tutorService.findById(id).isPresent()) {
			Tutor aux = tutorService.findById(id).get();
			aux.setNombre(tutor.getNombre());
			aux.setPrimerApellido(tutor.getPrimerApellido());
			aux.setSegundoApellido(tutor.getSegundoApellido());
			aux.setAlumnosTutor(tutor.getAlumnosTutor());
			return ResponseEntity.status(HttpStatus.OK).body(tutorService.save(aux));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el registro a actualizar.");
		}
	}
	
	@DeleteMapping("/delete_tutor/{id}")
	public ResponseEntity<?> deleteTutor(@PathVariable(value="id")Long id){
		if(tutorService.findById(id).isPresent()) {
			tutorService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el registro.");
		}
	}
	
	@PostMapping("/tutor")
	public ResponseEntity<?> getTutorEmail(@RequestBody Tutor tutor){
		Tutor aux = tutorService.findByEmail(tutor.getEmail());
		if(aux != null) {
			return ResponseEntity.status(HttpStatus.OK).body(aux);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tutores con ese email.");
		}
	}
}












