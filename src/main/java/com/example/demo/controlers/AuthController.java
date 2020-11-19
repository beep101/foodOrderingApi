package com.example.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.LoginInput;
import com.example.demo.service_interfaces.AuthServiceInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Autentifikacija"})
@RestController
public class AuthController {
	
	@Autowired
	private AuthServiceInterface authService;
	
	@ApiOperation(value="Login korisnika", notes = "Zahtjeva korisnicko ime i lozinku")
	@PostMapping("/login")
	public String login(@RequestBody LoginInput input){
		String token=authService.encodeToken(input.username, input.password);
		return token;
	}
}
