package com.example.demo.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "narudzba" database table.
 * 
 */
@Entity
@Table(name="narudzba")
@NamedQuery(name="Narudzba.findAll", query="SELECT n FROM Narudzba n")
public class Narudzba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="adresa")
	private String adresa;

	@Column(name="email")
	private String email;

	@Column(name="ime")
	private String ime;

	@Column(name="napomena")
	private String napomena;

	@Column(name="tel")
	private String tel;

	@ManyToOne
	@JoinColumn(name="restoran")
	private Restoran restoranBean;

	public Narudzba() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getNapomena() {
		return this.napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Restoran getRestoranBean() {
		return this.restoranBean;
	}

	public void setRestoranBean(Restoran restoranBean) {
		this.restoranBean = restoranBean;
	}

}