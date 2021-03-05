package com.jmarser.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jmarser.api.entity.Tutor;
import com.jmarser.api.repository.TutorRepository;

@Service
public class TutorServiceImpl implements TutorService{

	@Autowired
	private TutorRepository tutorRepository;
	
	@Override
	public List<Tutor> findAll() {
		return (List<Tutor>)tutorRepository.findAll();
	}

	@Override
	public Optional<Tutor> findById(Long id) {
		return tutorRepository.findById(id);
	}

	@Override
	public Tutor findByEmail(String email) {
		return tutorRepository.findByEmail(email);
	}

	@Override
	public Tutor save(Tutor entidad) {
		return tutorRepository.save(entidad);
	}

	@Override
	public Tutor update(Long id, Tutor entidad) {
		return tutorRepository.save(entidad);
	}

	@Override
	public void deleteById(Long id) {
		tutorRepository.deleteById(id);
	}

}
