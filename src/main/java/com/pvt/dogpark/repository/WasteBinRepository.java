package com.pvt.dogpark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pvt.dogpark.dao.WasteBinDAO;

public interface WasteBinRepository extends CrudRepository<WasteBinDAO, Long> {

	public List<WasteBinDAO> findAll();

	public Optional<WasteBinDAO> findById(Long id);

	@Query("FROM WasteBinDAO WHERE "
			+ "(6371392.896 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude))))"
			+ " < :distance ORDER BY "
			+ "(6371392.896 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude))))"
			+ " DESC")
	List<WasteBinDAO> findByPositionWithinDistance(Double latitude, Double longitude, Double distance);

}
