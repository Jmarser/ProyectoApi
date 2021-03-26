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
import com.jmarser.api.entity.Profesor;
import com.jmarser.api.service.AlumnoService;
import com.jmarser.api.service.LoginService;
import com.jmarser.api.service.ProfesorService;

@RestController
@RequestMapping("/api")
public class ProfesorController {

	@Autowired
	private ProfesorService profesorService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/profesores")
	public ResponseEntity<?> getAllProfesores(){
		List<Profesor> listado = profesorService.findAll();
		if(listado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listado);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay profesores registrados.");
		}
	}
	
	@GetMapping("/profesor/{id}")
	public ResponseEntity<?> getProfesorById(@PathVariable(value="id")Long id){
		if(profesorService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(profesorService.findById(id));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El profesor no esta registrado.");
		}
	}
	
	@PostMapping("/save_profesor")
	public ResponseEntity<?> saveProfesor(@RequestBody Profesor profesor){
		/*comprobamos que el profesor no este ya en la base de datos*/
		if(profesorService.findByEmail(profesor.getEmail())==null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.save(profesor));
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El profesor ya se encuentra registrado");
		}
	}
	
	@PutMapping("/update_profesor/{id}")
	public ResponseEntity<?> updateProfesor(@PathVariable(value="id")Long id, @RequestBody Profesor profesor){
		/*comprovamos que la id del profesor se encuentra en la base de datos*/
		if(profesorService.findById(id).isPresent()) {
			Profesor aux = profesorService.findById(id).get();
			aux.setNombre(profesor.getNombre());
			aux.setPrimerApellido(profesor.getPrimerApellido());
			aux.setSegundoApellido(profesor.getSegundoApellido());
			aux.setAlumnosProf(profesor.getAlumnosProf());
			return ResponseEntity.status(HttpStatus.OK).body(profesorService.save(aux));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El profesor no se encuentra registrado.");
		}
	}
	
	@DeleteMapping("/delete_profesor/{id}")
	public ResponseEntity<?> deleteProfesor(@PathVariable(value="id") Long id){
		/*comprobamos que el id del profesor se encuentra en la base de datos*/
		if(profesorService.findById(id).isPresent()) {
			profesorService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El profesor no se encuentra en la base de datos.");
		}
	}
	
	@PostMapping("/profesor")
	public ResponseEntity<?> getProfesorEmail(@RequestBody Profesor profesor){
		Profesor aux = profesorService.findByEmail(profesor.getEmail());
		if(aux != null) {
			/*Como es posible que haya alumnos asignados a este profesor que esten dados de baja, vamos a filtrar para 
			 * solamente pasar con el profesor la lista de alumnos dados de alta.*/
			List<Alumno> listAlumno = new ArrayList<>();
			Login log = null;
			/*recorremos el array de alumnos asignados al profesor, obtenemos sus estados de login, y los que esten activos los guardamos en 
			 * nuestro array provisional*/
			for (int i = 0; i <aux.getAlumnosProf().size(); i++) {
				log = loginService.findByEmail(aux.getAlumnosProf().get(i).getEmail());
				if(log != null) {
					if(log.isActivo()) {
						listAlumno.add(alumnoService.findByEmail(log.getEmail()));
					}
				}
			}
			/*Le pasamos el array provisional al profesor y lo devolvemos al usuario*/
			aux.setAlumnosProf(listAlumno);
			return ResponseEntity.status(HttpStatus.OK).body(aux);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El profesor no se encuentra en la base de datos.");
		}
	}
}













