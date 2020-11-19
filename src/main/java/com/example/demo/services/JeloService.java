package com.example.demo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.jpa.Jelo;
import com.example.demo.jpa.Restoran;
import com.example.demo.jpa.User;
import com.example.demo.repositories.JeloRepository;
import com.example.demo.service_interfaces.AuthServiceInterface;
import com.example.demo.service_interfaces.ImageStorageServiceInterface;
import com.example.demo.service_interfaces.JeloServiceInterface;

@Service
public class JeloService implements JeloServiceInterface {

	@Autowired
	private JeloRepository jeloRepo;
	@Autowired
	private AuthServiceInterface authService;
	@Autowired
	private ImageStorageServiceInterface imageService;
	@Autowired
	private RestoranService restoranService;

	@Override
	public Collection<Jelo> getJela() {
		return jeloRepo.findAll();
	}

	@Override
	public Jelo getJelo(int id) {
		return jeloRepo.findById(id).get();
	}

	@Override
	public Collection<Jelo> getActiveJela() {
		return jeloRepo.findByActiveTrue();
	}

	@Override
	public Collection<Jelo> getAllJelaByRestoranID(int restoranID) {

		List<Jelo> listaJela = (List<Jelo>) this.getJela();
		List<Jelo> listaJelaRestorana = new ArrayList<Jelo>();

		for (Jelo jelo : listaJela) {
			if (jelo.getRestoranBean().getId() == restoranID)
				listaJelaRestorana.add(jelo);
		}
		return listaJelaRestorana;
	}

	@Override
	public Collection<Jelo> getActiveJelaByRestoranID(int restoranID) {

		List<Jelo> listaAktJela = (List<Jelo>) this.getActiveJela();
		List<Jelo> listaAktJelaRestorana = new ArrayList<Jelo>();

		for (Jelo jelo : listaAktJela) {
			if (jelo.getRestoranBean().getId() == restoranID)
				listaAktJelaRestorana.add(jelo);
		}
		return listaAktJelaRestorana;
	}

	@Override
	public Collection<Jelo> getActiveJelaByKategorijaID(int kategorijaID) {

		List<Jelo> listaAktJela = (List<Jelo>) this.getActiveJela();
		List<Jelo> listaAktJelaKategorije = new ArrayList<Jelo>();

		for (Jelo jelo : listaAktJela) {
			if (jelo.getKategorijaBean().getId() == kategorijaID)
				listaAktJelaKategorije.add(jelo);
		}
		return listaAktJelaKategorije;
	}

	@Override
	public boolean addJelo(String token, Jelo jelo) {

		User authUser = authService.decodeToken(token);
		Collection<Restoran> restorani = restoranService.getRestorani();

		for (Restoran r : restorani) {
			if (r.getUsertbl().getId() == authUser.getId()) {
				jelo.setRestoranBean(r);
				jelo.setActive(true);
				jeloRepo.save(jelo);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean modifyJelo(String token, Jelo jelo) {

		User authUser = authService.decodeToken(token);
		Jelo check=getJelo(jelo.getId());
		if(check==null)
			return false;

		if (check.isActive()) {
			if (check.getRestoranBean().getUsertbl().getId() == authUser.getId() || authUser.getAdmin()) {
				jelo.setActive(true);
				jeloRepo.save(jelo);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteJelo(String token, int id) {

		User authUser = authService.decodeToken(token);
		Jelo jelo = getJelo(id);

		if (authUser.getAdmin() || jelo.getRestoranBean().getUsertbl().getId() == authUser.getId()) {
			jelo.setActive(false);
			jeloRepo.save(jelo);
			return true;
		}
		return false;
	}

	@Override
	public boolean addImage(String token, int id, MultipartFile file) {
		User authUser = authService.decodeToken(token);
		Jelo check = getJelo(id);
		if (!authUser.getAdmin())
			if (check.getRestoranBean().getUsertbl().getId() != authUser.getId())
				return false;
		byte[] image = null;
		try {
			image = file.getBytes();
		} catch (IOException e) {
			return false;
		}
		if (image != null)
			return imageService.addImage("jela", id, image);
		return false;
	}

	@Override
	public boolean deleteImage(String token, int id) {
		User authUser = authService.decodeToken(token);
		Jelo check = getJelo(id);
		if (check.getRestoranBean().getUsertbl().getId() == authUser.getId() || authUser.getAdmin())
			return imageService.deleteImage("jela", id);
		return false;
	}

}
