package com.pvt.dogpark.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pvt.dogpark.dao.UserDAO;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, Integer> {

	List<UserDAO> findAll();
	
	UserDAO findByUsername(String username);

}