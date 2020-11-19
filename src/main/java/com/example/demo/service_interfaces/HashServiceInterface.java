package com.example.demo.service_interfaces;

public interface HashServiceInterface {
	public String hash(String password);
	public boolean check(String password,String hash);
}
