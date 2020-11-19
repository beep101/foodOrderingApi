package com.example.demo.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "restoran" database table.
 * 
 */
@Entity
@Table(name="restoran")
@NamedQuery(name="Restoran.findAll", query="SELECT r FROM Restoran r")
public class Restoran implements Serializable {
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

	@Column(name="opis")
	private String opis;

	@Column(name="tel")
	private String tel;
	
	@Column(name="radnovrijeme")
	private String radnoVrijeme;
	
	@Column(name="cijenadostave")
	private int cijenaDostave;

	//uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="usr")
	private User usertbl;

	public Restoran() {
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

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public User getUsertbl() {
		return this.usertbl;
	}

	public void setUsertbl(User usertbl) {
		this.usertbl = usertbl;
	}
	
	public String getRadnoVrijeme() {
		return radnoVrijeme;
	}
	
	public int getCijenaDostave() {
		return cijenaDostave;
	}
	
	public void setRadnoVrijeme(String radnoVrijeme) {
		this.radnoVrijeme=radnoVrijeme;
	}
	
	public void setCijenaDostave(int cijenaDostave) {
		this.cijenaDostave=cijenaDostave;
	}

}