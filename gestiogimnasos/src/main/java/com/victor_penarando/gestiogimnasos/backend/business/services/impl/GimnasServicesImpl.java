package com.victor_penarando.gestiogimnasos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor_penarando.gestiogimnasos.backend.business.model.Gimnas;
import com.victor_penarando.gestiogimnasos.backend.business.services.GimnasServices;
import com.victor_penarando.gestiogimnasos.backend.integration.repositores.GimnasRepository;

@Service
public class GimnasServicesImpl implements GimnasServices {

	@Autowired
	private GimnasRepository gimnasRepository;
	
	@Override
	@Transactional
	public Long create(Gimnas gimnas) {
		
		if(gimnas.getId() != null) {
			throw new IllegalStateException("No se puede crear un gimnasio con código not null");
		}
		
		Long id = System.currentTimeMillis();
		gimnas.setId(id);
		
		gimnasRepository.save(gimnas);
		
		return id;
	}

	@Override
	public Optional<Gimnas> read(Long id) {	
		return gimnasRepository.findById(id);
	}

	@Override
	@Transactional
	public void update(Gimnas gimnas) {
		
		Long id = gimnas.getId();
		
		if(id == null) {
			throw new IllegalStateException("No se puede actualizar un gimnasio con código not null");
		}
		
		boolean existe = gimnasRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalStateException("El gimnasio con código " + id + " no existe. No se puede actualizar.");
		}
		
		gimnasRepository.save(gimnas);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		gimnasRepository.deleteById(id);
	}

	@Override
	public List<Gimnas> getAll() {
		return gimnasRepository.findAll();
	}

	@Override
	public List<Gimnas> getBetweenPreuRange(double min, double max) {
	return gimnasRepository.findByPreuBetweenOrderByPreuAsc(min, max);
	}
}