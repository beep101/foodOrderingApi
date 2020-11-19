package com.example.demo.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.jpa.Jelo;

public interface JeloRepository extends JpaRepository<Jelo, Integer>{
	public Collection<Jelo> findByActiveTrue();
}
