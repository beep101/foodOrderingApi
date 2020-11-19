package com.example.demo.services;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.jpa.Kategorija;
import com.example.demo.jpa.User;
import com.example.demo.repositories.KategorijaRepository;
import com.example.demo.service_interfaces.AuthServiceInterface;
import com.example.demo.service_interfaces.ImageStorageServiceInterface;
import com.example.demo.service_interfaces.KategorijaServiceInterface;

@Service
public class KategorijaService implements KategorijaServiceInterface {

	@Autowired
	private KategorijaRepository kategorijaRepo;
	@Autowired
	private AuthServiceInterface authService;
	@Autowired
	private ImageStorageServiceInterface imageService;

	@Override
	public Collection<Kategorija> getKategorije() {
		return kategorijaRepo.findAll();
	}

	@Override
	public Kategorija getKategorija(int id) {
		return kategorijaRepo.findById(id).get();
	}

	@Override
	public boolean addKategorija(String token, Kategorija kategorija) {
		User authUser = authService.decodeToken(token);
		if (authUser.getAdmin()) {
			kategorijaRepo.save(kategorija);
			return true;
		} else
			return false;
	}

	@Override
	public boolean modifyKategorija(String token, Kategorija kategorija) {
		User authUser = authService.decodeToken(token);
		if (authUser.getAdmin()) {
			kategorijaRepo.save(kategorija);
			return true;
		} else
			return false;
	}

	@Override
	public boolean deleteKategorija(String token, int id) {
		User authUser = authService.decodeToken(token);
		if (authUser.getAdmin()) {
			kategorijaRepo.deleteById(id);
			imageService.deleteImage("kategorije", id);
			return true;
		}
		return false;
	}

	@Override
	public boolean addImage(String token, int id, MultipartFile file) {
		User authUser = authService.decodeToken(token);
		if (!authUser.getAdmin())
			return false;
		byte[] image = null;
		try {
			image = file.getBytes();
		} catch (IOException e) {
			return false;
		}
		if (image != null)
			return imageService.addImage("kategorije", id, image);
		return false;
	}

	@Override
	public boolean deleteImage(String token, int id) {
		User authUser = authService.decodeToken(token);
		if (!authUser.getAdmin())
			return false;
		return imageService.deleteImage("kategorije", id);
	}

}
