package com.example.demo.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import com.example.demo.jpa.Narudzba;
import com.example.demo.jpa.Stavka;

public class NarudzbaFull extends Narudzba{
	private Collection<Stavka> stavke;
	
	public NarudzbaFull() {
		super();
	}
	
	public NarudzbaFull(Narudzba narudzba) {
		super();
		setNarudzba(narudzba);
	}
	
	public Collection<Stavka> getStavke(){
		return stavke;
	}
	
	public void setStavke(Collection<Stavka> stavke) {
		this.stavke=stavke;
	}
	
	public void setNarudzba(Narudzba narudzba) {
		this.setAdresa(narudzba.getAdresa());
		this.setEmail(narudzba.getEmail());
		this.setId(narudzba.getId());
		this.setIme(narudzba.getIme());
		this.setNapomena(narudzba.getNapomena());
		this.setTel(narudzba.getTel());
		this.setRestoranBean(narudzba.getRestoranBean());
	}
	
	public Narudzba getNarudzba() {
		Narudzba n=new Narudzba();
		n.setAdresa(getAdresa());
		n.setEmail(getEmail());
		n.setId(getId());
		n.setIme(getIme());
		n.setNapomena(getNapomena());
		n.setTel(getTel());
		n.setRestoranBean(getRestoranBean());
		return n;
	}
}
