package com.jmarser.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmarser.api.entity.Tutor;

@Repository
public interface TutorRepository extends CrudRepository<Tutor, Long>{

	public Tutor findByEmail(String email);
}
