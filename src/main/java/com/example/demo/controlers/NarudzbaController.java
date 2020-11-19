package com.example.demo.controlers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.Narudzba;
import com.example.demo.jpa.Restoran;
import com.example.demo.models.NarudzbaFull;
import com.example.demo.service_interfaces.NarudzbaServiceInterface;

@RestController
public class NarudzbaController {

	@Autowired
	private NarudzbaServiceInterface narudzbaService;

	@GetMapping("/narudzba")
	public Collection<NarudzbaFull> getNarudzbe(@RequestHeader("Authorization") String token) {
		return narudzbaService.getNarudzbe(token);
	}

	@GetMapping("/narudzba/{id}")
	public NarudzbaFull getNarudzba(@RequestHeader("Authorization") String token, @PathVariable int id) {
		return narudzbaService.getNarudzba(token, id);
	}

	@PostMapping("/narudzba")
	public ResponseEntity<NarudzbaFull> addNarudzba(@RequestBody NarudzbaFull narudzba) {
		if (narudzbaService.addNarudzba(narudzba))
			return new ResponseEntity<NarudzbaFull>(narudzba, HttpStatus.CREATED);
		else
			return new ResponseEntity<NarudzbaFull>(HttpStatus.BAD_REQUEST);
	}

	// @PutMapping("/narudzba")
	public NarudzbaFull modifyNarudzba(@RequestBody NarudzbaFull narudzba) {
		return null;
	}

	@DeleteMapping("/narudzba/{id}")
	public ResponseEntity<Narudzba> deleteNarudzba(@RequestHeader("Authorization") String token, @PathVariable int id) {
		if (narudzbaService.deleteNarudzba(token, id))
			return new ResponseEntity<Narudzba>(HttpStatus.OK);
		else
			return new ResponseEntity<Narudzba>(HttpStatus.FORBIDDEN);
	}
}
