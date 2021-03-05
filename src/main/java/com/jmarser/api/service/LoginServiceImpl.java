package com.jmarser.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmarser.api.entity.Login;
import com.jmarser.api.repository.LoginRepository;


@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Login> findAll() {
		
		return (List<Login>)loginRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Login> findById(Long id) {
		
		return loginRepository.findById(id);
	}

	@Override
	@Transactional
	public Login save(Login entidad) {
		
		return loginRepository.save(entidad);
	}

	@Override
	@Transactional
	public Login update(Long id, Login entidad) {
		
		return loginRepository.save(entidad);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		loginRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Login findByEmail(String email) {
		
		return loginRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public Login checkLogin(Login login) {
		
		return loginRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
	}

}
