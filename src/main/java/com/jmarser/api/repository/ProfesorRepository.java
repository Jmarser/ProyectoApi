package com.jmarser.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.jmarser.api.entity.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Long>{

	public Profesor findByEmail(String email);
}
