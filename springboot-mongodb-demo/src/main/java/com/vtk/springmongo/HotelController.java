package com.vtk.springmongo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	HotelRepository hotelRepository;

	public HotelController(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}
	
	@GetMapping("/all")
	public List<Hotel> getAll() {
		List<Hotel> hotels = this.hotelRepository.findAll();
		return hotels;
	}
	
	@PutMapping
	public void insert(@RequestBody Hotel hotel) {
		this.hotelRepository.insert(hotel);
	}

	@PostMapping
	public void update (@RequestBody Hotel hotel) {
		this.hotelRepository.save(hotel);
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable("id") String id) {
		this.hotelRepository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Hotel findByIds(@PathVariable("id") String id) {
		return this.hotelRepository.findByIds(id);
	}
	
	@GetMapping("/price/{maxPrice}")
	public List<Hotel> getByPricePerNight(@PathVariable("maxPrice") int maxPrice) {
		return this.hotelRepository.findByPricePerNightLessThan(maxPrice);
	}
	
	@GetMapping("/address/{city}")
	public List<Hotel> getByCity(@PathVariable("city") String city) {
		List<Hotel> hotels = this.hotelRepository.findByCity(city);
		return hotels;
	}

	@GetMapping("/country/{country}")
	public List<Hotel> getByCountry(@PathVariable("country") String country) {
		
		// create a query class QHotel
		QHotel qHotel = new QHotel("hotel");
		
		//using the query class to create the fillers
		BooleanExpression filterByCountry = qHotel.address.country.eq(country);
		
		// we than can pass the filter to findAll() method
		List<Hotel> hotels = (List<Hotel>)this.hotelRepository.findAll(filterByCountry);
		
		return hotels;
	}
	
	@GetMapping("/recommended")
	public List<Hotel> getRecommended () {
		
		final int maxPrice = 150;
		final int minRating = 7;
		
		QHotel qHotel = new QHotel("hotel");
		BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
		BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);
		
		List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));
		return hotels;
		
	}

}
