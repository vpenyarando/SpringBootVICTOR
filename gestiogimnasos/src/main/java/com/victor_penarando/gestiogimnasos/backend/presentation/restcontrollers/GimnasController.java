package com.victor_penarando.gestiogimnasos.backend.presentation.restcontrollers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.victor_penarando.gestiogimnasos.backend.business.model.Gimnas;
import com.victor_penarando.gestiogimnasos.backend.business.services.GimnasServices;
import com.victor_penarando.gestiogimnasos.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/gimnasos")
public class GimnasController {

	@Autowired
	private GimnasServices gimnasServices;
	
	@GetMapping
	public List<Gimnas> getAll(@RequestParam(name = "min", required = false) Double min, 
			 @RequestParam(name = "max", required = false) Double max){

	List<Gimnas> gimnasos = null;
	
	if(min != null && max != null) {
	gimnasos = gimnasServices.getBetweenPreuRange(min, max);
	} else {
	gimnasos = gimnasServices.getAll();
	}
	
	return gimnasos;
	}

	@GetMapping("/{id}")
	public Gimnas read(@PathVariable Long id) {
		
		Optional<Gimnas> optional = gimnasServices.read(id);
		
		if(!optional.isPresent()) {
			throw new PresentationException("No se encuentra el gimnasio con id " + id, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Gimnas gimnas, UriComponentsBuilder ucb) {
		
		Long codigo = null;
		
		try {
			codigo = gimnasServices.create(gimnas);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		URI uri = ucb.path("/gimnasos/{codigo}").build(codigo);
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		try {
			gimnasServices.delete(id);
		} catch(IllegalStateException e) {
			throw new PresentationException("No se encuentra el gimnasio con id [" + id + "]. No se ha podido eliminar.", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Gimnas gimnas) {
		
		try {
			gimnasServices.update(gimnas);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
}
