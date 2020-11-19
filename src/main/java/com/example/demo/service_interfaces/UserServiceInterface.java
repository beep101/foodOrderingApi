package com.example.demo.service_interfaces;

import com.example.demo.jpa.User;
import java.util.Collection;

public interface UserServiceInterface {
	Collection<User> getUsers(String token);

	Collection<User> getAdmins(String token);
	
	User getUser(String token, int id);

	boolean addUser(User user);

	boolean modifyUser(String token, User user);

	boolean deleteUser(String token, int id);
}
