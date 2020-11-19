package com.example.demo.services;

import java.security.Key;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Restoran;
import com.example.demo.jpa.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service_interfaces.AuthServiceInterface;
import com.example.demo.service_interfaces.HashServiceInterface;
import com.example.demo.service_interfaces.RestoranServiceInterface;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService implements AuthServiceInterface {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RestoranServiceInterface restoranService;
	@Autowired
	private HashServiceInterface hashService;

	private SignatureAlgorithm alg = SignatureAlgorithm.HS256;

	private String keyString = "pjeskovi123pjeskovi456pjeskovi789";
	byte[] keyBytes = DatatypeConverter.parseBase64Binary(keyString);
	Key key = new SecretKeySpec(keyBytes, alg.getJcaName());

	@Override
	public String encodeToken(String username, String password) {
		User user = getUser(username, password);
		if (user == null)
			return null;
		long nowMilis = System.currentTimeMillis();
		int restoranId = -1;
		Collection<Restoran> restorani = restoranService.getRestorani();
		for (Restoran restoran : restorani)
			if (restoran.getUsertbl().getId() == user.getId()) {
				restoranId = restoran.getId();
				break;
			}
		JwtBuilder builder = Jwts.builder().setId(Integer.toString(user.getId())).claim("username", user.getUname())
				.claim("restoran", restoranId).claim("type", user.getAdmin() ? "A" : "R").signWith(alg, key);
		return "Bearer " + builder.compact();
	}

	@Override
	public User decodeToken(String token) {
		token = token.replace("Bearer ", "");
		User user = null;
		System.out.println("***********" + token + "***********");
		try {
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			System.out.println(claims.getId());
			user = userRepo.findById(Integer.parseInt(claims.getId())).get();
		} catch (Exception e) {
			return null;
		}
		return user;
	}

	private User getUser(String username, String password) {
		List<User> users = userRepo.findByUname(username);
		if (users.isEmpty())
			return null;
		else {
			User user = users.get(0);
			if (hashService.check(password, user.getPasswd()))
				return user;
			else
				return null;
		}
	}

}
