package com.example.demo.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.jpa.*;

public interface UserRepository extends JpaRepository<User,Integer>{
	public List<User> findByUname(String uname);
}

