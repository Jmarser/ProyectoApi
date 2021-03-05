package com.jmarser.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jmarser.api.entity.Profesor;
import com.jmarser.api.repository.ProfesorRepository;

@Service
public class ProfesorServiceImpl implements ProfesorService{

	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Override
	public List<Profesor> findAll() {
		return (List<Profesor>)profesorRepository.findAll();
	}

	@Override
	public Optional<Profesor> findById(Long id) {
		return profesorRepository.findById(id);
	}

	@Override
	public Profesor findByEmail(String email) {
		return profesorRepository.findByEmail(email);
	}

	@Override
	public Profesor save(Profesor entidad) {
		return profesorRepository.save(entidad);
	}

	@Override
	public Profesor update(Long id, Profesor entidad) {
		return profesorRepository.save(entidad);
	}

	@Override
	public void deleteById(Long id) {
		profesorRepository.deleteById(id);
	}

}
