package com.victor_penarando.gestiogimnasos.backend.presentation.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.victor_penarando.gestiogimnasos.backend.business.model.Gimnas;
import com.victor_penarando.gestiogimnasos.backend.business.services.GimnasServices;
import com.victor_penarando.gestiogimnasos.backend.presentation.config.RespostaError;

@RestController
public class GimnasController {

	@Autowired
	private GimnasServices gimnasServices;
	
	@GetMapping("/gimnasos")
	public List<Gimnas> getAll(){
		return gimnasServices.getAll();
	}

	@GetMapping("/gimnasos/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {
		
		if(id > 500) {
			throw new RuntimeException("El número " + id + " no es vàlid.");
		}
		
		Optional<Gimnas> optional = gimnasServices.read(id);
		
		if (optional.isEmpty()) {
			RespostaError respostaError = new RespostaError("No es troba el gimnàs amb id " + id);
			return new ResponseEntity<>(respostaError, HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(optional.get());
	}
	
	// ****************************************************
	//
	// Gestió d'excepcions
	//
	// ****************************************************
	
	@ExceptionHandler({IllegalArgumentException.class, ClassCastException.class})
	public ResponseEntity<?> gestor1(Exception e){
		return ResponseEntity.badRequest().body(new RespostaError(e.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> gestor2(Exception e){
		return ResponseEntity.badRequest().body(new RespostaError(e.getMessage()));
	}
	
}
