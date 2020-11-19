package com.example.demo.services;

import java.nio.charset.Charset;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.example.demo.service_interfaces.HashServiceInterface;

@Service
public class HashService implements HashServiceInterface{

	@Override
	public String hash(String password) {
		//salt
		byte[] array = new byte[16];
	    new Random(System.currentTimeMillis()).nextBytes(array);
		String salt=new String(array, Charset.forName("UTF-8"));
		
		
		String hashingInput=salt+password;
		String hash = DigestUtils.sha256Hex(hashingInput);
		return salt+hash;
	}

	@Override
	public boolean check(String password, String hash) {
		//salt
		String salt=hash.substring(0, 16);
		
		String hashingInput=salt+password;
		String hashingOutput = DigestUtils.sha256Hex(hashingInput);
		System.out.println(hash);
		System.out.println(salt+hashingOutput);
		if(hash.equals(salt+hashingOutput))
			return true;
		else
			return false;
	}

}
