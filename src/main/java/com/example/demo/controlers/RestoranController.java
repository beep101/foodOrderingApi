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

import com.example.demo.jpa.Restoran;
import com.example.demo.service_interfaces.RestoranServiceInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Upravljanje podacima o restoranima" })
@RestController
public class RestoranController {

	@Autowired
	private RestoranServiceInterface restoranService;

	@ApiOperation(value = "Lista svih restorana", notes = "Svi mogu pristupiti")
	@GetMapping("/restoran")
	public Collection<Restoran> getRestorani() {
		return restoranService.getRestorani();
	}

	@ApiOperation(value = "Restoran dobavljen prema ID", notes = "Svi mogu pristupiti")
	@GetMapping("/restoran/{id}")
	public Restoran getRestoran(@PathVariable int id) {
		return restoranService.getRestoran(id);
	}

	@ApiOperation(value = "Pretraga restorana", notes = "Svi mogu pristupiti")
	@GetMapping("/restoranPretraga/{term}")
	public Collection<Restoran> findRestoran(@PathVariable String term) {
		return restoranService.findRestoran(term);
	}

	@ApiOperation(value = "Kreiranje novog restorana", notes = "Samo korisnik koji je ulogovan moze kreirati restoran, restoran se veze za njegov nalog, nije moguce imati vise od jednog restorana")
	@PostMapping("/restoran")
	public ResponseEntity<Restoran> addRestoran(@RequestHeader("Authorization") String token,
			@RequestBody Restoran restoran) {
		if (restoranService.addRestoran(token, restoran))
			return new ResponseEntity<Restoran>(restoran, HttpStatus.CREATED);
		else
			return new ResponseEntity<Restoran>(HttpStatus.FORBIDDEN);
	}

	@ApiOperation(value = "Izmjena podataka o restoranu", notes = "Samo korisnik ciji je restoran i admin mogu izmjentit podatke")
	@PutMapping("/restoran")
	public ResponseEntity<Restoran> modifyRestoran(@RequestHeader("Authorization") String token,
			@RequestBody Restoran restoran) {
		if (restoranService.modifyRestoran(token, restoran))
			return new ResponseEntity<Restoran>(HttpStatus.OK);
		else
			return new ResponseEntity<Restoran>(HttpStatus.FORBIDDEN);
	}

	@ApiOperation(value = "Brisanje restorana", notes = "Samo korisnik ciji je restoran i admin mogu izbrisati podatke")
	@DeleteMapping("/restoran/{id}")
	public ResponseEntity<Restoran> deleteRestoran(@RequestHeader("Authorization") String token, @PathVariable int id) {
		if (restoranService.deleteRestoran(token, id))
			return new ResponseEntity<Restoran>(HttpStatus.OK);
		else
			return new ResponseEntity<Restoran>(HttpStatus.FORBIDDEN);
	}

	@ApiOperation(value = "Dodavanje slike restorana, mora biti JPG format", notes = "Samo korisnik ciji je restoran i admin mogu dodati sliku, slika se nalazi na amazon S3 bucketu link je:  s3.eu-central-1.amazonaws.com/donesi.projekat/restorani/{ID restorana}.jpg")
	@PostMapping("/restoran/{id}/img")
	public ResponseEntity<Restoran> addImage(@RequestHeader("Authorization") String token, @PathVariable int id,
			@RequestParam("file") MultipartFile file) {
		if (restoranService.addImage(token, id, file))
			return new ResponseEntity<Restoran>(HttpStatus.OK);
		else
			return new ResponseEntity<Restoran>(HttpStatus.FORBIDDEN);
	}

	@ApiOperation(value = "Brisanje slike restorana", notes = "Samo korisnik ciji je restoran i admin mogu izbrisati sliku")
	@DeleteMapping("/restoran/{id}/img")
	public ResponseEntity<Restoran> deleteImage(@RequestHeader("Authorization") String token, @PathVariable int id) {
		if (restoranService.deleteImage(token, id))
			return new ResponseEntity<Restoran>(HttpStatus.OK);
		else
			return new ResponseEntity<Restoran>(HttpStatus.FORBIDDEN);
	}

}
