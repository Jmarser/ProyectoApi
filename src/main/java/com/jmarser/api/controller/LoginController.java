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
import com.jmarser.api.entity.Login;
import com.jmarser.api.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/logins")
	public ResponseEntity<?> getAllLogin(){
		
		List<Login> listado = loginService.findAll();
		
		if(listado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listado);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay registros en login");
		}
	}
	
	@GetMapping("/login/{id}")
	public ResponseEntity<?> getLoginById(@PathVariable(value = "id")Long id){
		
		if(loginService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(loginService.findById(id));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}
	}
	
	@PostMapping("/save_login")
	public ResponseEntity<?> saveLogin(@RequestBody Login login){
		/*Primero validamos que el login no se encuentre ya en la base de datos*/
		if(loginService.findByEmail(login.getEmail()) == null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(loginService.save(login));
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El login ya se encuentra registrado.");
		}
	}
	
	@PutMapping("/update_login/{id}")
	public ResponseEntity<?> updateLogin(@PathVariable(value="id")Long id, @RequestBody Login login){
		/*validamos que el objeto que queremos actualizar se encuentra en la base de datos*/
		if(loginService.findById(id).isPresent()) {
			Login aux = loginService.findById(id).get();
			aux.setEmail(login.getEmail());
			aux.setPassword(login.getPassword());
			aux.setRol(login.getRol());
			return ResponseEntity.status(HttpStatus.OK).body(loginService.save(aux));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El login no se encuentra en la base de datos.");
		}
	}

	@DeleteMapping("/delete_login/{id}")
	public ResponseEntity<?> deleteLogin(@PathVariable(value="id")Long id){
		if(loginService.findById(id).isPresent()) {
			loginService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el registro a eliminar.");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> checkLogin(@RequestBody Login login){
		/*validamos que el login existe en la base de datos*/
		Login aux = loginService.checkLogin(login);
		if(aux != null) {
			if(aux.isActivo()) {
				return ResponseEntity.status(HttpStatus.OK).body(aux);
			}else {
				return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Usuario dado de baja");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no esta registrado");
		}
	}
}







