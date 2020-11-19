package com.example.demo.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.jpa.*;

public interface RestoranRepository extends JpaRepository<Restoran, Integer>{
	@Query(value="SELECT * FROM restoran r WHERE lower(r.ime) like lower(concat('%',?1,'%')) or lower(r.opis) like lower(concat('%',?2,'%'))", nativeQuery = true)
	public Collection<Restoran> findByImeOrOpis(String ime, String opis);
}
