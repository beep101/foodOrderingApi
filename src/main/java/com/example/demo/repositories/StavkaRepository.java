package com.example.demo.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.jpa.Stavka;

public interface StavkaRepository extends JpaRepository<Stavka, Integer>{
	
	@Query(value="SELECT * FROM stavka s WHERE s.narudzba = ?1", nativeQuery = true)
	public Collection<Stavka> findByNarudzbaId(Integer id);
}
