package com.propify.challenge.repository;

import java.util.Set;

import com.propify.challenge.model.Property;

public interface PropertyRepositoryCustom {

	public Set<Property> search(String minRentPrice, String maxRentPrice);

}
