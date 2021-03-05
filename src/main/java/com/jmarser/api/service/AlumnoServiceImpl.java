package com.jmarser.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jmarser.api.entity.Alumno;
import com.jmarser.api.repository.AlumnoRepository;


@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	private AlumnoRepository AlumnoRepository;
	
	@Override
	public List<Alumno> findAll() {
		return (List<Alumno>)AlumnoRepository.findAll();
	}

	@Override
	public Optional<Alumno> findById(Long id) {
		return AlumnoRepository.findById(id);
	}

	@Override
	public Alumno findByEmail(String email) {
		return AlumnoRepository.findByEmail(email);
	}

	@Override
	public Alumno save(Alumno entidad) {
		return AlumnoRepository.save(entidad);
	}

	@Override
	public Alumno update(Long id, Alumno entidad) {
		return AlumnoRepository.save(entidad);
	}

	@Override
	public void deleteById(Long id) {
		AlumnoRepository.deleteById(id);
		
	}

}
