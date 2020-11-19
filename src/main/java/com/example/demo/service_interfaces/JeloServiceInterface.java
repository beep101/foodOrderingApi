package com.example.demo.service_interfaces;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.jpa.Jelo;

public interface JeloServiceInterface {
	Collection<Jelo> getJela();

	Jelo getJelo(int id);
	
	Collection<Jelo> getActiveJela();
	
	Collection<Jelo> getAllJelaByRestoranID(int restoranID);
	
	Collection<Jelo> getActiveJelaByRestoranID(int restoranID);
	
	Collection<Jelo> getActiveJelaByKategorijaID(int kategorijaID);

	boolean addJelo(String token, Jelo jelo);

	boolean modifyJelo(String token, Jelo jelo);

	boolean deleteJelo(String token, int id);

	boolean addImage(String token, int id, MultipartFile file);

	boolean deleteImage(String token, int id);
}
