package com.victor_penarando.gestiogimnasos.backend.presentation.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor_penarando.gestiogimnasos.backend.business.model.Tipus;
import com.victor_penarando.gestiogimnasos.backend.business.model.Gimnas;
import com.victor_penarando.gestiogimnasos.backend.business.services.GimnasServices;
import com.victor_penarando.gestiogimnasos.backend.presentation.config.RespostaError;

@WebMvcTest(controllers=GimnasController.class)
public class GimnasControllerTest {
	
	@Autowired
	private MockMvc miniPostman;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private GimnasServices gimnasServices;
	
	private Gimnas gimnas1;
	private Gimnas gimnas2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void pedimos_todos_los_productos() throws Exception {
		
		// Arrange
		
		List<Gimnas> gimnas = Arrays.asList(gimnas1, gimnas2);
		when(gimnasServices.getAll()).thenReturn(gimnas);
		
		// Act
		
		MvcResult respuesta = miniPostman.perform(get("/gimnasos").contentType("application/json"))
											.andExpect(status().isOk())
											.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(gimnas);
		
		// Assert
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
		
	}
	
	@Test
	void pedimos_todos_los_productos_entre_rango_precios() throws Exception {
				
		List<Gimnas> gimnas = Arrays.asList(gimnas1, gimnas2);
		when(gimnasServices.getBetweenPreuRange(10, 500)).thenReturn(gimnas);
			
		MvcResult respuesta = miniPostman.perform(get("/gimnasos").param("min", "10")
																   .param("max","500")
																   .contentType("application/json"))
											.andExpect(status().isOk())
											.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(gimnas);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
		
	}
	
	@Test
	void obtenemos_producto_a_partir_de_su_id() throws Exception{
		
		when(gimnasServices.read(100L)).thenReturn(Optional.of(gimnas1));
		
		MvcResult respuesta = miniPostman.perform(get("/gimnasos/100").contentType("application/json"))
									.andExpect(status().isOk())
									.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(gimnas1);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	
	}
	
	@Test
	void solicitamos_producto_a_partir_de_un_id_inexistente() throws Exception {
		
		when(gimnasServices.read(100L)).thenReturn(Optional.empty());
		
		MvcResult respuesta = miniPostman.perform(get("/gimnasos/100").contentType("application/json"))
									.andExpect(status().isNotFound())
									.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(new RespostaError("No se encuentra el gimnasio con id 100"));
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	}
	
	@Test
	void crea_producto_ok() throws Exception {
		
		gimnas1.setId(null);
		
		when(gimnasServices.create(gimnas1)).thenReturn(1033L);
		
		String requestBody = objectMapper.writeValueAsString(gimnas1);
		
		miniPostman.perform(post("/productos").content(requestBody).contentType("application/json"))
						.andExpect(status().isCreated())
						.andExpect(header().string("Location","http://localhost/gimnasos/1033"));
	}
	
	@Test
	void crear_producto_con_id_NO_NULL() throws Exception{
		
		when(gimnasServices.create(gimnas1)).thenThrow(new IllegalStateException("Problema con el id..."));
		
		String requestBody = objectMapper.writeValueAsString(gimnas1);
		
		MvcResult respuesta = miniPostman.perform(post("/gimnasos").content(requestBody).contentType("application/json"))
						.andExpect(status().isBadRequest())
						.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(new RespostaError("Problema con el id..."));
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	}
	
	@Test
	void eliminamos_producto_ok() throws Exception{
		
		miniPostman.perform(delete("/gimnasos/789")).andExpect(status().isNoContent());
		
		verify(gimnasServices, times(1)).delete(789L);
	}
	
	@Test
	void eliminamos_producto_no_existente() throws Exception{
		
		Mockito.doThrow(new IllegalStateException("xxxx")).when(gimnasServices).delete(789L);
		
		MvcResult respuesta = miniPostman.perform(delete("/productos/789"))
								.andExpect(status().isNotFound())
								.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString();
		String respuestaEsperada = objectMapper.writeValueAsString(new RespostaError("No se encuentra el gimnasio con id [789]. No se ha podido eliminar."));
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
		
	}
	
	// **************************************************************
	//
	// Private Methods
	//
	// **************************************************************
	
	private void initObjects() {
		
		gimnas1 = new Gimnas();
		gimnas1.setId(100L);
		gimnas1.setNom("Alfombrilla Mouse CR7");
		gimnas1.setObert(true);
		gimnas1.setPreu(20.0);
		gimnas1.setTipus(Tipus.MULTIESPORTIU);
		gimnas1.setDataInauguracio(new Date(10000000000L));
		
		gimnas2 = new Gimnas();
		gimnas2.setId(101L);
		gimnas2.setNom("Ordenador Epson D30");
		gimnas2.setObert(false);
		gimnas2.setPreu(400.0);
		gimnas2.setTipus(Tipus.MONOESPORTIU);
		gimnas2.setDataInauguracio(new Date(10000000001L));
		
	}
}
