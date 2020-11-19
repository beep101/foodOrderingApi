package com.example.demo.service_interfaces;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.jpa.Kategorija;

public interface KategorijaServiceInterface {
	Collection<Kategorija> getKategorije();

	Kategorija getKategorija(int id);

	boolean addKategorija(String token, Kategorija kategorija);

	boolean modifyKategorija(String token, Kategorija kategorija);

	boolean deleteKategorija(String token, int id);

	boolean addImage(String token, int id, MultipartFile file);

	boolean deleteImage(String token, int id);
}
