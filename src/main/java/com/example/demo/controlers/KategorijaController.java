package com.example.demo.controlers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.jpa.Kategorija;
import com.example.demo.service_interfaces.KategorijaServiceInterface;

@RestController
public class KategorijaController {

	@Autowired
	private KategorijaServiceInterface kategorijaService;

	@GetMapping("/kategorija")
	public Collection<Kategorija> getKateorije() {
		return kategorijaService.getKategorije();
	}

	@GetMapping("/kategorija/{id}")
	public Kategorija getKategorija(@PathVariable int id) {
		return kategorijaService.getKategorija(id);
	}

	@PostMapping("/kategorija")
	public ResponseEntity<Kategorija> addKategorija(@RequestHeader("Authorization") String token,
			@RequestBody Kategorija kategorija) {
		if (kategorijaService.addKategorija(token, kategorija))
			return new ResponseEntity<Kategorija>(kategorija, HttpStatus.CREATED);
		else
			return new ResponseEntity<Kategorija>(HttpStatus.FORBIDDEN);
	}

	@PutMapping("/kategorija")
	public ResponseEntity<Kategorija> moifyKategorija(@RequestHeader("Authorization") String token,
			@RequestBody Kategorija kategorija) {
		if (kategorijaService.modifyKategorija(token, kategorija))
			return new ResponseEntity<Kategorija>(HttpStatus.OK);
		else
			return new ResponseEntity<Kategorija>(HttpStatus.FORBIDDEN);
	}

	@DeleteMapping("/kategorija/{id}")
	public ResponseEntity<Kategorija> deleteKategorija(@RequestHeader("Authorization") String token,
			@PathVariable int id) {
		if (kategorijaService.deleteKategorija(token, id))
			return new ResponseEntity<Kategorija>(HttpStatus.OK);
		else
			return new ResponseEntity<Kategorija>(HttpStatus.FORBIDDEN);
	}

	@PostMapping("/kategorija/{id}/img")
	public ResponseEntity<Kategorija> addImage(@RequestHeader("Authorization") String token, @PathVariable int id,
			@RequestParam("file") MultipartFile file) {
		if (kategorijaService.addImage(token, id, file))
			return new ResponseEntity<Kategorija>(HttpStatus.OK);
		else
			return new ResponseEntity<Kategorija>(HttpStatus.FORBIDDEN);
	}

	@DeleteMapping("/kategorija/{id}/img")
	public ResponseEntity<Kategorija> deleteImage(@RequestHeader("Authorization") String token, @PathVariable int id) {
		if (kategorijaService.deleteImage(token, id))
			return new ResponseEntity<Kategorija>(HttpStatus.OK);
		else
			return new ResponseEntity<Kategorija>(HttpStatus.FORBIDDEN);
	}
}
