package com.victor_penarando.gestiogimnasos.backend.business.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.victor_penarando.gestiogimnasos.backend.business.model.Gimnas;
import com.victor_penarando.gestiogimnasos.backend.business.model.Tipus;
import com.victor_penarando.gestiogimnasos.backend.business.services.GimnasServices;

@Service
public class GimnasServicesImpl implements GimnasServices {

	private final TreeMap<Long, Gimnas> GIMNASOS = new TreeMap<>();
	
	public GimnasServicesImpl() {
		init();
	}
	
	@Override
	public Long create(Gimnas gimnas) {
		
		Long id = GIMNASOS.lastKey() + 1;
		
		gimnas.setId(id);
		
		GIMNASOS.put(gimnas.getId(), gimnas);
		
		return id;
	}

	@Override
	public Optional<Gimnas> read(Long id) {
		return Optional.ofNullable(GIMNASOS.get(id));
	}

	@Override
	public List<Gimnas> getAll() {
		return new ArrayList<>(GIMNASOS.values());
	}
	
	// ***************************************************************
	//
	// Private Methods
	//
	// ***************************************************************

	private void init() {
		
		Gimnas p1 = new Gimnas();
		Gimnas p2 = new Gimnas();
		Gimnas p3 = new Gimnas();
		
		p1.setId(10L);
		p1.setNom("McFit");
		p1.setQuotaMensual(30.0);
		p1.setAdreca("Carrer Tarragona, 18, Ripollet (Barcelona)");
		p1.setObert(true);
		p1.setTipus(Tipus.MONOESPORTIU);
		p1.setDataInauguracio(new Date(100000000L));
		
		p2.setId(11L);
		p2.setNom("Poliesportiu Ripollet");
		p2.setQuotaMensual(20.0);
		p2.setAdreca("Carrer de Balmes, 2, Ripollet (Barcelona)");
		p2.setObert(true);
		p2.setTipus(Tipus.MULTIESPORTIU);
		p2.setDataInauguracio(new Date(100000100L));
		
		p3.setId(12L);
		p3.setNom("SAF");
		p3.setQuotaMensual(10.0);
		p3.setAdreca("Avinguda de l'Eix Central, Cerdanyola del Vallès (Barcelona)");
		p3.setObert(false);
		p3.setTipus(Tipus.MULTIESPORTIU);
		p3.setDataInauguracio(new Date(100000600L));
		
		GIMNASOS.put(p1.getId(), p1);
		GIMNASOS.put(p2.getId(), p2);
		GIMNASOS.put(p3.getId(), p3);
		
	}
}