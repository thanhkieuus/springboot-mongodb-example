package com.vtk.springmongo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbSeeder implements CommandLineRunner {

	private HotelRepository hotelRepository;
	

	public DbSeeder(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		
		Hotel mariot = new Hotel(
				"Mariot",
				130,
				new Address("Paris", "France"),
				Arrays.asList(
						new Review("John", 8, false),
						new Review("Mary", 7, true)
				)				
		);

		Hotel saigon = new Hotel(
				"Saigon",
				100,
				new Address("Saigon", "Vietnam"),
				Arrays.asList(
						new Review("Jason", 10, true)
				)				
		);
		Hotel sofitel = new Hotel(
				"Sofitel",
				200,
				new Address("Rome", "Italy"),
				new ArrayList<>()
		);
		
		// drop all hotels
		this.hotelRepository.deleteAll();
		
		// add hotels to db
		List<Hotel> hotels = Arrays.asList(mariot, saigon, sofitel);
		this.hotelRepository.saveAll(hotels);
	}

}
