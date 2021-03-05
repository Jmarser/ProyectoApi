package com.jmarser.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmarser.api.entity.Alumno;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
	
	public Alumno findByEmail(String email);

}
