package com.example.demo.controlers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.Stavka;
import com.example.demo.service_interfaces.StavkaServiceInterface;

//@RestController
public class StavkaController {

	@Autowired
	private StavkaServiceInterface stavkaService;

	// @("/stavka")
	public Collection<Stavka> getStavke() {
		return null;
	}

	// @GetMapping("/stavka/{id}")
	public Stavka getStavka(@PathVariable int stavka) {
		return null;
	}

	//@PostMapping("/stavka")
	public ResponseEntity<Stavka> addStavka(@RequestBody Stavka stavka) {
		return null;
	}

	// @PutMapping("/stavka")
	public ResponseEntity<Stavka> modifyStavka(@RequestBody Stavka stavka) {
		return null;
	}

	// @DeleteMapping("/stavka")
	public ResponseEntity<Stavka> deleteStavka(@RequestBody Stavka stavka) {
		return null;
	}
}
