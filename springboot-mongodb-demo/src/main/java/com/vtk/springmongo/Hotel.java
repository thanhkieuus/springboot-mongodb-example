package com.vtk.springmongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Hotels")
public class Hotel {
	
	@Id
	private String ids;
	private String name;
	@Indexed(direction = IndexDirection.ASCENDING)
	private int pricePerNight;
	private Address address;
	private List<Review> reviews;
		
	protected Hotel() {
		this.reviews = new ArrayList<>();
	}

	public Hotel(String name, int pricePerNight, Address address, List<Review> reviews) {
		this.name = name;
		this.pricePerNight = pricePerNight;
		this.address = address;
		this.reviews = reviews;
	}

	public String getIds() {
		return ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(int pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
}
