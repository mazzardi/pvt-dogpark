package com.pvt.dogpark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pvt.dogpark.dao.DogParkDAO;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DogParkRepository extends CrudRepository<DogParkDAO, Integer> {

	public List<DogParkDAO> findAll();

	public Optional<DogParkDAO> findById(Long id);

	public List<DogParkDAO> findByName(String name);

	@Query("FROM DogParkDAO WHERE "
			+ "(6371392.896 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude))))"
			+ " < :distance ORDER BY "
			+ "(6371392.896 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude))))"
			+ " DESC")
	List<DogParkDAO> findByPositionWithinDistance(Double latitude, Double longitude, Double distance);

}