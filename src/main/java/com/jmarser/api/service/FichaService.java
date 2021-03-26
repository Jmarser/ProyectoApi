package com.jmarser.api.service;

import java.util.List;
import java.util.Optional;

import com.jmarser.api.entity.Ficha;

public interface FichaService {

	public List<Ficha> findByAlumnoId(Long id);
	
	public List<Ficha> findByAll();
	
	public Optional<Ficha> findById(Long id);
	
	public Ficha updateFicha(Long id, Ficha ficha);
	
	public Ficha saveFicha(Ficha ficha);
	
	public void deleteById(Long id);
	
	public Ficha checkFicha(Long id, String fecha);
	
}
