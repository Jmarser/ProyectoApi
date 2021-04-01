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
import com.jmarser.api.entity.Alumno;
import com.jmarser.api.mapper.Mapper;
import com.jmarser.api.model.ModelAlumno;
import com.jmarser.api.model.ModelProfesor;
import com.jmarser.api.model.ModelTutor;
import com.jmarser.api.service.AlumnoService;
import com.jmarser.api.service.ProfesorService;
import com.jmarser.api.service.TutorService;


@RestController
@RequestMapping("/api")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private ProfesorService profesorService;
	
	@Autowired
	private TutorService tutorService;
	
	@GetMapping("/alumnos")
	public ResponseEntity<?> getAllAlumnos(){
		List<Alumno> listado = alumnoService.findAll();
		if(listado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listado);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay alumnos guardados.");
		}
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> getAlumnoById(@PathVariable(value="id")Long id){
		if(alumnoService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(alumnoService.findById(id));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado.");
		}
	}
	
	@PostMapping("/save_alumno")
	public ResponseEntity<?> saveAlumno(@RequestBody Alumno alumno){
		/*validamos que el alumno no se encuentre en la base de datos*/
		if(alumnoService.findByEmail(alumno.getEmail())== null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumno));
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El alumno ya se encuentra en la base de datos.");
		}
	}
	
	@PutMapping("/update_alumno/{id}")
	public ResponseEntity<?> updateAlumno(@PathVariable(value = "id")Long id, @RequestBody Alumno alumno){
		
		if(alumnoService.findById(id).isPresent()) {
			Alumno aux = alumnoService.findById(id).get();
			aux.setNombre(alumno.getNombre());
			aux.setPrimerApellido(alumno.getEmail());
			aux.setSegundoApellido(alumno.getSegundoApellido());
			aux.setEmail(alumno.getEmail());
			return ResponseEntity.status(HttpStatus.OK).body(alumnoService.save(aux));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado.");
		}
	}
	
	@DeleteMapping("/delete_alumno/{id}")
	public ResponseEntity<?> deleteAlumno(@PathVariable(value="id")Long id){
		
		if(alumnoService.findById(id).isPresent()) {
			alumnoService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado.");
		}
	}
	
	@PostMapping("/alumno")
	public ResponseEntity<?> getAlumnoEmail(@RequestBody Alumno alumno){
		
		Alumno aux = alumnoService.findByEmail(alumno.getEmail());
		if(aux!= null) {
			ModelProfesor modelProfesor = new ModelProfesor(profesorService.findById(aux.getProfesorId()).get());
			ModelTutor modelTutor = new ModelTutor(tutorService.findById(aux.getTutorId()).get());
			ModelAlumno modelAlumno = new ModelAlumno();
			modelAlumno = Mapper.convertirAlumno(aux, modelProfesor, modelTutor);
			return ResponseEntity.status(HttpStatus.OK).body(modelAlumno);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no registrado.");
		}
	}
}











