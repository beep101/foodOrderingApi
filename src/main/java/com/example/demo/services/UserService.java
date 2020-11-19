package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service_interfaces.AuthServiceInterface;
import com.example.demo.service_interfaces.HashServiceInterface;
import com.example.demo.service_interfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AuthServiceInterface authService;
	@Autowired
	private HashServiceInterface hashService;

	@Override
	public Collection<User> getUsers(String token) {
		User auth = decodeUser(token);
		if (auth.getAdmin())
			return userRepo.findAll();
		return new ArrayList<User>();
	}

	@Override
	public Collection<User> getAdmins(String token) {

		List<User> listaAdmina = new ArrayList<User>();

		User auth = decodeUser(token);
		if (auth.getAdmin()) {
			List<User> listaSvih = userRepo.findAll();

			for (User u : listaSvih) {
				if (u.getAdmin())
					listaAdmina.add(u);
			}
		}

		return listaAdmina;
	}

	@Override
	public User getUser(String token, int id) {
		User auth = decodeUser(token);
		if (auth.getAdmin() || auth.getId() == id)
			return userRepo.findById(id).get();
		return null;
	}

	@Override
	public boolean addUser(User user) {
		user.setPasswd(hashService.hash(user.getPasswd()));
		User savedUser = userRepo.save(user);
		return true;
	}

	@Override
	public boolean modifyUser(String token, User user) {
		User auth = decodeUser(token);
		if (auth.getId() == user.getId() || auth.getAdmin()) {
			user.setPasswd(hashService.hash(user.getPasswd()));
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(String token, int id) {
		User auth = decodeUser(token);
		if (auth.getId() == id || auth.getAdmin()) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}

	private User decodeUser(String token) {
		return authService.decodeToken(token);
	}

}
