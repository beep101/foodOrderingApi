package com.example.demo.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "stavka" database table.
 * 
 */
@Entity
@Table(name="stavka")
@NamedQuery(name="Stavka.findAll", query="SELECT s FROM Stavka s")
public class Stavka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="kolicina")
	private int kolicina;

	//uni-directional many-to-one association to Jelo
	@ManyToOne
	@JoinColumn(name="jelo")
	private Jelo jeloBean;

	//uni-directional many-to-one association to Narudzba
	@ManyToOne
	@JoinColumn(name="narudzba")
	private Narudzba narudzbaBean;

	public Stavka() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Jelo getJeloBean() {
		return this.jeloBean;
	}

	public void setJeloBean(Jelo jeloBean) {
		this.jeloBean = jeloBean;
	}

	public Narudzba getNarudzbaBean() {
		return this.narudzbaBean;
	}

	public void setNarudzbaBean(Narudzba narudzbaBean) {
		this.narudzbaBean = narudzbaBean;
	}

}