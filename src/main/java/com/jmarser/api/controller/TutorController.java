package com.jmarser.api.controller;

import java.util.ArrayList;
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

import com.jmarser.api.entity.Alumno;
import com.jmarser.api.entity.Login;
import com.jmarser.api.entity.Tutor;
import com.jmarser.api.service.AlumnoService;
import com.jmarser.api.service.LoginService;
import com.jmarser.api.service.TutorService;

@RestController
@RequestMapping("/api")
public class TutorController {

	@Autowired
	private TutorService tutorService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private AlumnoService alumnoService;
	
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
			/*Como es posible que haya alumnos asignados a este tutor que esten dados de baja, vamos a filtrar para 
			 * solamente pasar con el profesor la lista de alumnos dados de alta.*/
			List<Alumno> listAlumno = new ArrayList<>();
			Login log = null;
			/*recorremos el array de alumnos asignados al profesor, obtenemos sus estados de login, y los que esten activos los guardamos en 
			 * nuestro array provisional*/
			for (int i = 0; i <aux.getAlumnosTutor().size(); i++) {
				log = loginService.findByEmail(aux.getAlumnosTutor().get(i).getEmail());
				if(log != null) {
					if(log.isActivo()) {
						listAlumno.add(alumnoService.findByEmail(log.getEmail()));
					}
				}
			}
			/*Le pasamos el array provisional al profesor y lo devolvemos al usuario*/
			aux.setAlumnosTutor(listAlumno);
			return ResponseEntity.status(HttpStatus.OK).body(aux);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tutores con ese email.");
		}
	}
}












