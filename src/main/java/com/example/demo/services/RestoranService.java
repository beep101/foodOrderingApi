package com.example.demo.services;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.jpa.Restoran;
import com.example.demo.jpa.User;
import com.example.demo.repositories.RestoranRepository;
import com.example.demo.service_interfaces.AuthServiceInterface;
import com.example.demo.service_interfaces.ImageStorageServiceInterface;
import com.example.demo.service_interfaces.RestoranServiceInterface;
import com.example.demo.service_interfaces.UserServiceInterface;

@Service
public class RestoranService implements RestoranServiceInterface {

	@Autowired
	private RestoranRepository restoranRepository;
	@Autowired
	private AuthServiceInterface authService;
	@Autowired
	private ImageStorageServiceInterface imageService;
	@Autowired
	private UserServiceInterface userService;

	@Override
	public Collection<Restoran> getRestorani() {
		return restoranRepository.findAll();
	}

	@Override
	public Restoran getRestoran(int id) {
		return restoranRepository.findById(id).get();
	}

	@Override
	public Collection<Restoran> findRestoran(String term) {
		return restoranRepository.findByImeOrOpis(term, term);
	}

	@Override
	public boolean addRestoran(String token, Restoran restroan) {
		User authUser = authService.decodeToken(token);
		Collection<Restoran> restorani = getRestorani();
		for (Restoran r : restorani)
			if (r.getUsertbl().getId() == authUser.getId())
				return false;
		if (!authUser.getAdmin())
			restroan.setUsertbl(authUser);
		restoranRepository.save(restroan);
		return true;
	}

	@Override
	public boolean modifyRestoran(String token, Restoran restoran) {
		User authUser = authService.decodeToken(token);
		Restoran check = getRestoran(restoran.getId());
		if (check.getUsertbl().getId() == authUser.getId() || authUser.getAdmin()) {
			if (!authUser.getAdmin())
				restoran.setUsertbl(authUser);
			restoranRepository.save(restoran);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteRestoran(String token, int id) {

		User authUser = authService.decodeToken(token);
		int userId = -1;
		try {
			userId = getRestoran(id).getUsertbl().getId();
		} catch (Exception e) {
			return false;
		}

		if (authUser.getAdmin()) {
			restoranRepository.deleteById(id);
			imageService.deleteImage("restorani", id);
			userService.deleteUser(token, userId);
			return true;
		}
		return false;
	}

	@Override
	public boolean addImage(String token, int id, MultipartFile file) {
		User authUser = authService.decodeToken(token);
		Restoran check = getRestoran(id);
		if (!authUser.getAdmin())
			if (check.getUsertbl().getId() != authUser.getId())
				return false;
		byte[] image = null;
		try {
			image = file.getBytes();
		} catch (IOException e) {
			return false;
		}
		if (image != null)
			return imageService.addImage("restorani", id, image);
		return false;
	}

	@Override
	public boolean deleteImage(String token, int id) {
		User authUser = authService.decodeToken(token);
		Restoran check = getRestoran(id);
		if (check.getUsertbl().getId() == authUser.getId() || authUser.getAdmin())
			return imageService.deleteImage("restorani", id);
		return false;
	}

}
