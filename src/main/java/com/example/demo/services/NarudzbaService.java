package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Narudzba;
import com.example.demo.jpa.Stavka;
import com.example.demo.jpa.User;
import com.example.demo.models.NarudzbaFull;
import com.example.demo.repositories.NarudzbaRepository;
import com.example.demo.repositories.StavkaRepository;
import com.example.demo.service_interfaces.AuthServiceInterface;
import com.example.demo.service_interfaces.NarudzbaServiceInterface;

@Service
public class NarudzbaService implements NarudzbaServiceInterface {

	@Autowired
	private NarudzbaRepository narudzbaRepo;
	@Autowired
	private StavkaRepository stavkaRepo;
	@Autowired
	private AuthServiceInterface authService;

	@Override
	public Collection<NarudzbaFull> getNarudzbe(String token) {
		User authUser = authService.decodeToken(token);
		Collection<Narudzba> narudzbe=narudzbaRepo.findAll();
		Collection<NarudzbaFull> narudzbeFull=new ArrayList<NarudzbaFull>();
		for(Narudzba n:narudzbe) {
			if(!authUser.getAdmin())
				if(n.getRestoranBean().getUsertbl().getId()!=authUser.getId())
					continue;
			NarudzbaFull nf=new NarudzbaFull(n);
			nf.setStavke(stavkaRepo.findByNarudzbaId(nf.getId()));
			narudzbeFull.add(nf);
		}
		return narudzbeFull;
	}

	@Override
	public NarudzbaFull getNarudzba(String token,int id) {
		User authUser = authService.decodeToken(token);
		NarudzbaFull narudzba=new NarudzbaFull(narudzbaRepo.findById(id).get());
		narudzba.setStavke(stavkaRepo.findByNarudzbaId(narudzba.getId()));
		if(!authUser.getAdmin())
			if(narudzba.getRestoranBean().getUsertbl().getId()!=authUser.getId())
				return null;
		return narudzba;
	}

	@Override
	public boolean addNarudzba(NarudzbaFull narudzba) {
		int restoranId=0;
		if(narudzba.getStavke().isEmpty())
			return false;
		for(Stavka stavka:narudzba.getStavke())
			if(narudzba.getRestoranBean().getId()!=stavka.getJeloBean().getRestoranBean().getId())
				return false;
		Narudzba n= narudzbaRepo.save(narudzba.getNarudzba());
		for(Stavka stavka:narudzba.getStavke()) {
			stavka.setNarudzbaBean(n);
			stavkaRepo.save(stavka);
		}
		return true;
	}

	//add later
	@Override
	public boolean modifyNarduzba(String token, NarudzbaFull narudzba) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNarudzba(String token, int id) {
		User authUser = authService.decodeToken(token);
		if(authUser.getAdmin()) {
			narudzbaRepo.deleteById(id);
			return true;
		}
		return false;

	}

}
