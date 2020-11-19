package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.jpa.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer>{
}