package com.jmarser.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmarser.api.entity.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long>{
	
	public Login findByEmail(String email);
	
	public Login findByEmailAndPassword(String email, String password);
	

}
