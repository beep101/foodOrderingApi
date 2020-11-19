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

import com.example.demo.jpa.Jelo;
import com.example.demo.service_interfaces.JeloServiceInterface;
import com.sun.xml.bind.v2.TODO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
public class JeloController {

	@Autowired
	private JeloServiceInterface jeloService;

	@ApiOperation(value = "Lista svih aktivnih jela", notes = "Svi mogu pristupiti")
	@GetMapping("/jelo")
	public Collection<Jelo> getJela() {
		return jeloService.getActiveJela();
	}

	@ApiOperation(value = "Lista svih jela (aktivnih i neaktivnih)", notes = "Svi mogu pristupiti")
	@GetMapping("/jeloAll")
	public Collection<Jelo> getAllJela() {
		return jeloService.getJela();
	}

	@ApiOperation(value = "Jelo dobavljeno prema ID", notes = "Svi mogu pristupiti")
	@GetMapping("/jelo/{id}")
	public Jelo getJelo(@PathVariable int id) {
		return jeloService.getJelo(id);
	}

	@ApiOperation(value = "Jela dobavljena prema ID restorana", notes = "Svi mogu pristupiti")
	@GetMapping("/{restoran}/jelaAll")
	public Collection<Jelo> getAllJelaByRestoran(@PathVariable int restoran) {
		return jeloService.getAllJelaByRestoranID(restoran);
	}

	@ApiOperation(value = "Aktivna jela dobavljena prema ID restorana", notes = "Svi mogu pristupiti")
	@GetMapping("/{restoran}/jela")
	public Collection<Jelo> getJelaByRestoran(@PathVariable int restoran) {
		return jeloService.getActiveJelaByRestoranID(restoran);
	}

	@ApiOperation(value = "Aktivna jela dobavljena prema ID kategorije", notes = "Svi mogu pristupiti")
	@GetMapping("/{kategorija}/jeloByKat")
	public Collection<Jelo> getJelaByKategorija(@PathVariable int kategorija) {
		return jeloService.getActiveJelaByKategorijaID(kategorija);
	}

	@ApiOperation(value = "Kreiranje novog jela", notes = "Samo korisnik koji je ulogovan moze kreirati jelo, restoran se veze za njegov restoran")
	@PostMapping("/jelo")
	public ResponseEntity<Jelo> addJelo(@RequestHeader("Authorization") String token, @RequestBody Jelo jelo) {
		if (jeloService.addJelo(token, jelo))
			return new ResponseEntity<Jelo>(jelo, HttpStatus.CREATED);
		else
			return new ResponseEntity<Jelo>(HttpStatus.FORBIDDEN);
	}

	@ApiOperation(value = "Izmena podataka o jelu", notes = "Samo korisnik ciji je restoran u kom se jelo nalazi i admin mogu izmjentit podatke")
	@PutMapping("/jelo")
	public ResponseEntity<Jelo> modifyJelo(@RequestHeader("Authorization") String token, @RequestBody Jelo jelo) {
		if (jeloService.modifyJelo(token, jelo))
			return new ResponseEntity<Jelo>(HttpStatus.OK);
		else
			return new ResponseEntity<Jelo>(HttpStatus.FORBIDDEN);
	}

	@ApiOperation(value = "Brisanje jela", notes = "Samo korisnik ciji je restoran u kom se nalazi jelo i admin mogu izbrisati podatke")
	@DeleteMapping("/jelo/{id}")
	public ResponseEntity<Jelo> deleteJelo(@RequestHeader("Authorization") String token, @PathVariable int id) {
		if (jeloService.deleteJelo(token, id))
			return new ResponseEntity<Jelo>(HttpStatus.OK);
		else
			return new ResponseEntity<Jelo>(HttpStatus.FORBIDDEN);
	}

	@PostMapping("/jelo/{id}/img")
	public ResponseEntity<Jelo> addImage(@RequestHeader("Authorization") String token, @PathVariable int id,
			@RequestParam("file") MultipartFile file) {
		if (jeloService.addImage(token, id, file))
			return new ResponseEntity<Jelo>(HttpStatus.OK);
		else
			return new ResponseEntity<Jelo>(HttpStatus.FORBIDDEN);
	}

	@DeleteMapping("/jelo/{id}/img")
	public ResponseEntity<Jelo> deleteImage(@RequestHeader("Authorization") String token, @PathVariable int id) {
		if (jeloService.deleteImage(token, id))
			return new ResponseEntity<Jelo>(HttpStatus.OK);
		else
			return new ResponseEntity<Jelo>(HttpStatus.FORBIDDEN);
	}
}