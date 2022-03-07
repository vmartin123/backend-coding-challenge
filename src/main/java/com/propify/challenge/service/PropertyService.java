package com.propify.challenge.service;

import java.util.Set;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;

public interface PropertyService {

	public Set<Property> getProperties();

	public void insert(Property property);

	public Set<Property> search(String minRentPrice, String maxRentPrice);

	public Property findById(Long id);

	public void update(Property property);

	public void delete(Long id);

	public PropertyReport propertyReport();

}
