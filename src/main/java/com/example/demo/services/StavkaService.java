package com.example.demo.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Stavka;
import com.example.demo.repositories.StavkaRepository;
import com.example.demo.service_interfaces.AuthServiceInterface;
import com.example.demo.service_interfaces.StavkaServiceInterface;

@Service
public class StavkaService implements StavkaServiceInterface {

	@Autowired
	private StavkaRepository stavkaRepo;
	@Autowired
	private AuthServiceInterface authService;

	@Override
	public Collection<Stavka> getStavke() {
		//add auth
		return stavkaRepo.findAll();
	}

	@Override
	public Stavka getStavka(int id) {
		//unneeded
		return stavkaRepo.findById(id).get();
	}

	@Override
	public boolean addStavka(Stavka stavka) {
		//add auth
		stavkaRepo.save(stavka);
		return true;
	}

	@Override
	public boolean modifyStavka(Stavka stavka) {
		//unneeded
		return false;
	}

	@Override
	public boolean deleteStavka(int id) {
		//unneeded
		return false;
	}

}
