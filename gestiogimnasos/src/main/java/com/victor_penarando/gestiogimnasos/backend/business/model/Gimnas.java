package com.victor_penarando.gestiogimnasos.backend.business.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="GIMNASOS")
public class Gimnas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    
    private String nom;
    private String adreca;
    private Double preu;
    
    @Enumerated(EnumType.STRING)
    private Tipus tipus;
    
    @Temporal(TemporalType.DATE)
    private Date dataInauguracio;
    
    private boolean obert;

    public Gimnas() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public Double getPreu() {
        return preu;
    }

    public void setPreu(Double quotaMensual) {
        this.preu = quotaMensual;
    }

    public Date getDataInauguracio() {
        return dataInauguracio;
    }

    public void setDataInauguracio(Date dataInauguracio) {
        this.dataInauguracio = dataInauguracio;
    }
    
    public Tipus getFamilia() {
		return tipus;
	}

	public void setTipus(Tipus tipus) {
		this.tipus = tipus;
	}

    public boolean isObert() {
        return obert;
    }

    public void setObert(boolean obert) {
        this.obert = obert;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Gimnas other = (Gimnas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Gimnas [id=" + id + ", nom=" + nom + ", adreca=" + adreca + ", preu=" + preu
                + ", dataInauguracio=" + dataInauguracio + ", tipus=" + tipus + ", obert=" + obert + "]";
    }
}

