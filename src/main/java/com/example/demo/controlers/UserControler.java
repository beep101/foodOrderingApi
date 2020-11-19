package com.example.demo.controlers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.example.demo.jpa.*;
import com.example.demo.service_interfaces.UserServiceInterface;

@Api(tags = { "Korisnicki nalozi" })
@RestController
public class UserControler {

	@Autowired
	private UserServiceInterface userService;

	@ApiOperation(value = "Lista svih korisnika", notes = "Samo admin ima pristup")
	@GetMapping("/user")
	public Collection<User> getUsers(@RequestHeader("Authorization") String token) {
		return userService.getUsers(token);
	}

	@ApiOperation(value = "Lista svih admina", notes = "Samo admin ima pristup")
	@GetMapping("/admins")
	public Collection<User> getAdmins(@RequestHeader("Authorization") String token) {
		return userService.getAdmins(token);
	}

	@ApiOperation(value = "Korisnik dobavljen prema ID", notes = "Admin i korisnik ciji nalog se dobavlja imaju pristup")
	@GetMapping("/user/{id}")
	public User getUser(@RequestHeader("Authorization") String token, @PathVariable int id) {
		return userService.getUser(token, id);
	}

	@ApiOperation(value = "Kreiranje korisnickog naloga", notes = "Svi mogu pristupiti")
	@PostMapping("/user")
	public ResponseEntity<User> insertUser(@RequestBody User user) {
		if (userService.addUser(user))
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		else
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}

	@ApiOperation(value = "Izmjena podataka korisnika", notes = "Admin i korisnik ciji nalog se mjenja imaju pristup")
	@PutMapping("/user")
	public ResponseEntity<User> modifyUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
		if (userService.modifyUser(token, user))
			return new ResponseEntity<User>(HttpStatus.OK);
		else
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}

	@ApiOperation(value = "Brisnje korisnickog naloga", notes = "Admin i korisnik ciji nalog se brise imaju pristup")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUser(@RequestHeader("Authorization") String token, @PathVariable int id) {
		if (userService.deleteUser(token, id))
			return new ResponseEntity<User>(HttpStatus.OK);
		else
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}
}
