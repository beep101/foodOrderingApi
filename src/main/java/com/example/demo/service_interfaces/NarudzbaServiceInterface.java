package com.example.demo.service_interfaces;

import java.util.Collection;

import com.example.demo.jpa.Narudzba;
import com.example.demo.models.NarudzbaFull;

public interface NarudzbaServiceInterface {
	Collection<NarudzbaFull> getNarudzbe(String token);

	NarudzbaFull getNarudzba(String token,int id);

	boolean addNarudzba(NarudzbaFull narudzba);

	boolean modifyNarduzba(String token, NarudzbaFull narudzba);

	boolean deleteNarudzba(String token, int id);
}
