package com.example.demo.service_interfaces;

import java.util.Collection;

import com.example.demo.jpa.Stavka;

public interface StavkaServiceInterface {
	Collection<Stavka> getStavke();

	Stavka getStavka(int id);

	boolean addStavka(Stavka stavka);

	boolean modifyStavka(Stavka stavka);

	boolean deleteStavka(int id);
}
