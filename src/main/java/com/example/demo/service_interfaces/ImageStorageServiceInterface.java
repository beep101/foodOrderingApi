package com.example.demo.service_interfaces;

import java.awt.Image;
import java.util.Collection;

public interface ImageStorageServiceInterface {
	boolean addImage(String type, int id, byte[] image);

	boolean deleteImage(String type, int id);

}
