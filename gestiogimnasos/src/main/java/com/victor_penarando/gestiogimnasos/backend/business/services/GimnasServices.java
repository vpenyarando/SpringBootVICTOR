package com.victor_penarando.gestiogimnasos.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.victor_penarando.gestiogimnasos.backend.business.model.Gimnas;


public interface GimnasServices {

	Long create(Gimnas gimnas);	    // C
	Optional<Gimnas> read(Long id);   // R
	void update(Gimnas gimnas);
	void delete (Long id);
	
	List<Gimnas> getAll();
	List<Gimnas> getBetweenPreuRange(double min, double max);
	
}
