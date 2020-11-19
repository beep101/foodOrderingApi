package com.example.demo.service_interfaces;

import com.example.demo.jpa.User;

public interface AuthServiceInterface {
	String encodeToken(String username, String password);

	User decodeToken(String token);
}
