package com.propify.challenge.service;

import java.util.Set;

import com.propify.challenge.model.Property;

public interface PropertyService {

	public void insert(Property property);

	public Set<Property> search(String minRentPrice, String maxRentPrice);

	public Property findById(Long id);

	public void update(Property property);

	public void delete(Long id);

}
