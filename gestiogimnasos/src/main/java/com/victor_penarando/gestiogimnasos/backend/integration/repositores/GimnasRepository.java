package com.victor_penarando.gestiogimnasos.backend.integration.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.victor_penarando.gestiogimnasos.backend.business.model.Gimnas;
import com.victor_penarando.gestiogimnasos.backend.business.model.Tipus;
import com.victor_penarando.gestiogimnasos.backend.business.model.dtos.Gimnas1DTO;

public interface GimnasRepository extends JpaRepository<Gimnas, Long>{
	
	List<Gimnas> findByPreuBetweenOrderByPreuAsc(double min, double max);
	
	List<Gimnas> findObertsByTipus(Tipus tipus);

	//List<Gimnas1DTO> getAllGimnas1DTO();
	
}
