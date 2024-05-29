package com.victor_penarando.gestiogimnasos.backend.presentation.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor_penarando.gestiogimnasos.backend.integration.repositores.GimnasRepository;

@RestController
public class BorrameController {

	@Autowired
	private GimnasRepository gimnasRepository;
	
}
