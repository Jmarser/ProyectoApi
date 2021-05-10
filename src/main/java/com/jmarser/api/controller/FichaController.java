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

import com.jmarser.api.entity.Ficha;
import com.jmarser.api.service.FichaService;

@RestController
@RequestMapping("/api")
public class FichaController {

	@Autowired
	private FichaService fichaService;
	
	@GetMapping("/fichas")
	public ResponseEntity<?> getAllFichas(){
		List<Ficha> listado = fichaService.findByAll();
		if(listado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listado);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay fichas registradas.");
		}
	}
	
	@GetMapping("/fichas_alumno/{id}")
	public ResponseEntity<?> getFichasAlumno(@PathVariable(value="id")Long id){
		List<Ficha> listado = fichaService.findByAlumnoId(id);
		
		if(listado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listado);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay fichas del alumno.");
		}
	}
	
	@PostMapping("/save_ficha")
	public ResponseEntity<?> saveFicha(@RequestBody Ficha ficha){
		Ficha aux = fichaService.checkFicha(ficha.getAlumnoId(), ficha.getFecha());
		if(aux != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya hay una ficha creada para esa fecha");
		}else {
			Ficha aux2 = fichaService.saveFicha(ficha);
			if(aux2 != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(aux2);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido guardar la ficha.");
			}
		}
	}
	
	@PutMapping("/update_ficha/{id}")
	public ResponseEntity<?> updateFicha(@PathVariable(value="id")Long id, @RequestBody Ficha ficha){
		/*Comprobamos que la ficha esta en la base de datos*/
		if(fichaService.findById(id).isPresent()) {
			Ficha aux = fichaService.findById(id).get();
			aux.setHoras(ficha.getHoras());
			aux.setFecha(ficha.getFecha());
			aux.setDescripcion(ficha.getDescripcion());
			aux.setObservaciones(ficha.getObservaciones());
			aux.setFirmaAlumno(aux.isFirmaAlumno());
			aux.setFirmaProf(ficha.isFirmaProf());
			aux.setFirmaTutor(ficha.isFirmaTutor());
			return ResponseEntity.status(HttpStatus.OK).body(fichaService.saveFicha(aux));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ficha no existe.");
		}
	}
	
	@DeleteMapping("/delete_ficha/{id}")
	public ResponseEntity<?> deleteFicha(@PathVariable(value="id")Long id){
		/*comprobamos que la ficha este en la base de datos*/
		if(fichaService.findById(id).isPresent()) {
			fichaService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ficha no existe.");
		}
	}
	
	
	
}
