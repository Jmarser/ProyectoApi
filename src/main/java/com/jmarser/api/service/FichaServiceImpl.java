package com.jmarser.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmarser.api.entity.Ficha;
import com.jmarser.api.repository.FichaRepository;

@Service
public class FichaServiceImpl implements FichaService{

	@Autowired
	private FichaRepository fichaRepository;
	
	@Override
	public List<Ficha> findByAlumnoId(Long id) {
		return fichaRepository.findByAlumnoId(id);
	}

	@Override
	public List<Ficha> findByAll() {
		return (List<Ficha>)fichaRepository.findAll();
	}

	@Override
	public Optional<Ficha> findById(Long id) {
		return fichaRepository.findById(id);
	}

	@Override
	public Ficha updateFicha(Long id, Ficha ficha) {
		return fichaRepository.save(ficha);
	}

	@Override
	public Ficha saveFicha(Ficha ficha) {
		return fichaRepository.save(ficha);
	}

	@Override
	public void deleteById(Long id) {
		fichaRepository.deleteById(id);		
	}

}
