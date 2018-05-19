package com.vtk.springmongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {
	
	//Optional<Hotel> findById(String id);
	Hotel findByIds(String id);
	List<Hotel> findByPricePerNightLessThan(int maxPrice);
	
	@Query(value = "{'address.city':?0}")
	List<Hotel> findByCity(String city);

}
