package com.poi.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poi.apirest.models.PointOfInterest;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Integer> {
	@Query(value = "SELECT * FROM point_of_interest p WHERE sqrt(POWER((p.x - ?1),2) + POWER((p.y - ?2),2)) <= ?3", nativeQuery = true)
	List<PointOfInterest> findNearbyPOIs(Integer x, Integer y, Integer maxDistanceAllowed);
}
