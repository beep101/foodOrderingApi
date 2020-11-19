package com.example.demo.service_interfaces;

import com.example.demo.jpa.Restoran;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

public interface RestoranServiceInterface {
	Collection<Restoran> getRestorani();

	Restoran getRestoran(int id);

	boolean addRestoran(String token, Restoran resroan);

	boolean modifyRestoran(String token, Restoran restoran);

	boolean deleteRestoran(String token, int id);

	Collection<Restoran> findRestoran(String term);

	boolean addImage(String token, int id, MultipartFile file);

	boolean deleteImage(String token, int id);
}
