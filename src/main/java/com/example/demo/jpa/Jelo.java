package com.example.demo.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.math.BigDecimal;


/**
 * The persistent class for the "jelo" database table.
 * 
 */
@Entity
@Table(name="jelo")
@NamedQuery(name="Jelo.findAll", query="SELECT j FROM Jelo j")
public class Jelo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="cijena")	
	private BigDecimal cijena;
	
	@Column(name="ime")
	private String ime;
	
	@Column(name="opis")	
	private String opis;
	
	@Column(name="sastav")	
	private String sastav;
	
	@JsonIgnore
	@Column(name="active")
	private boolean active;

	@ManyToOne
	@JoinColumn(name="kategorija")
	private Kategorija kategorijaBean;
	
	@ManyToOne
	@JoinColumn(name="restoran")	
	private Restoran restoranBean;

	public Jelo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCijena() {
		return this.cijena;
	}

	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSastav() {
		return this.sastav;
	}

	public void setSastav(String sastav) {
		this.sastav = sastav;
	}

	public Kategorija getKategorijaBean() {
		return this.kategorijaBean;
	}

	public void setKategorijaBean(Kategorija kategorijaBean) {
		this.kategorijaBean = kategorijaBean;
	}

	public Restoran getRestoranBean() {
		return this.restoranBean;
	}

	public void setRestoranBean(Restoran restoranBean) {
		this.restoranBean = restoranBean;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}