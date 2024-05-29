package com.victor_penarando.gestiogimnasos.backend.business.model.dtos;

import java.io.Serializable;

public class Gimnas1DTO implements Serializable{
private static final long serialVersionUID = 1L;
	
	private String nom;
	private double preu;
	
	public Gimnas1DTO(String nom, double preu) {
		this.nom = nom;
		this.preu = preu;
	}

	public String getNom() {
		return nom;
	}

	public double getPreu() {
		return preu;
	}

	@Override
	public String toString() {
		return "Gimnas1DTO [nom=" + nom + ", preu=" + preu + "]";
	}
}
