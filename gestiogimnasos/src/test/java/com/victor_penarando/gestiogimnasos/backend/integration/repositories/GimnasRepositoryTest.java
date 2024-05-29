package com.victor_penarando.gestiogimnasos.backend.integration.repositories;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.victor_penarando.gestiogimnasos.backend.business.model.Tipus;
import com.victor_penarando.gestiogimnasos.backend.business.model.Gimnas;
import com.victor_penarando.gestiogimnasos.backend.business.model.dtos.Gimnas1DTO;
import com.victor_penarando.gestiogimnasos.backend.integration.repositores.GimnasRepository;

@DataJpaTest
@Sql(scripts= {"/data/h2/schema_test.sql","/data/h2/data_test.sql"})
public class GimnasRepositoryTest {

	@Autowired
	private GimnasRepository gimnasRepository;
	
	private Gimnas gim1;
	private Gimnas gim2;
	private Gimnas gim3;
	private Gimnas gim4;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void obtenemos_productos_entre_rango_de_precios_en_orden_ascendente() {
		
		List<Gimnas> gimnasos = gimnasRepository.findByPreuBetweenOrderByPreuAsc(20.0, 500.0);
		
		assertEquals(2, gimnasos.size());
			
		assertTrue(gim4.equals(gimnasos.get(0)));
		assertTrue(gim1.equals(gimnasos.get(1)));
		
	}
	
	@Test
	void obtenermos_productos_descatalogados_por_familia() {
		
		List<Gimnas> gimnasos = gimnasRepository.findObertsByTipus(Tipus.MONOESPORTIU);
		
		assertEquals(1, gimnasos.size());
		
		assertTrue(gim2.equals(gimnasos.get(0)));
	}
	
//	@Test
//	void obtenemos_todos_los_Producto1DTO() {
//		
//		List<Gimnas1DTO> gimnasos1DTO = gimnasRepository.getAllGimnas1DTO();
//		
//		for(Gimnas1DTO gimnas1DTO: gimnasos1DTO) {
//			System.err.println(gimnasos1DTO);
//		}
//	}
	
	// **************************************************************
	//
	// Private Methods
	//
	// **************************************************************
	
	private void initObjects() {
		
		gim1 = new Gimnas();
		gim2 = new Gimnas();
		gim3 = new Gimnas();
		gim4 = new Gimnas();
		
		gim1.setId(100L);
		gim2.setId(101L);
		gim3.setId(102L);
		gim4.setId(103L);
		
	}
	
}