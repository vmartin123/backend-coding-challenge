package com.propify.challenge.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.model.PropertyType;
import com.propify.challenge.repository.PropertyRepository;
import com.propify.challenge.service.AlertService;
import com.propify.challenge.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	PropertyService propertyService;

	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	AlertService alertService;

	@Override
	public Set<Property> getProperties() {
		return new HashSet<Property>(propertyRepository.findAll());
	}

	@Override
	public void insert(Property property) {
		if (property.getRentPrice() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rent price must be greater than 0");
		}

		property.setCreateTime(new Date());
		property.setCode(property.getCode().toUpperCase());

		propertyRepository.save(property);
		System.out.println("CREATED: " + property.getId());
	}

	@Override
	public Set<Property> search(String minRentPrice, String maxRentPrice) {
		return propertyRepository.search(minRentPrice, maxRentPrice);
	}

	@Override
	public Property findById(Long id) {
		Optional<Property> property = propertyRepository.findById(id);

		if (property.isPresent()) {
			return propertyRepository.findById(id).get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property " + id + " not found");
		}
	}

	@Override
	public void update(Property property) {
		Optional<Property> propertyDb = propertyRepository.findById(property.getId());

		if (propertyDb.isPresent()) {
			propertyDb.get().setId(property.getId());
			propertyDb.get().setType(property.getType());
			propertyDb.get().setRentPrice(property.getRentPrice());
			propertyDb.get().setAddress(property.getAddress());
			propertyDb.get().setEmailAddress(property.getEmailAddress());
			propertyDb.get().setCode(property.getCode());

			propertyRepository.save(propertyDb.get());
			System.out.println("UPDATED: " + property.getId());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property " + property.getId() + " not found");
		}
	}

	@Override
	public void delete(Long id) {
		Optional<Property> property = propertyRepository.findById(id);

		if (property.isPresent()) {
			propertyRepository.deleteById(id);
			System.out.println("DELETED: " + id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property " + id + " not found");
		}

		alertService.sendPropertyDeletedAlert(id);
	}

	@Override
	public PropertyReport propertyReport() {
		Collection<Property> properties = search(null, null);
		PropertyReport propertyReport = new PropertyReport();

		int totalProperties = properties.size();

		HashMap<PropertyType, Integer> propertiesMap = new HashMap<>();
		propertiesMap.put(PropertyType.SINGLE_FAMILY, 0);
		propertiesMap.put(PropertyType.MULTI_FAMILY, 0);
		propertiesMap.put(PropertyType.CONDOMINIUM, 0);
		propertiesMap.put(PropertyType.TOWN_HOUSE, 0);

		double averageRentPrice = 0;
		int IllinoisTotal = 0;

		for (Property property : properties) {
			propertiesMap.put(property.getType(), propertiesMap.get(property.getType()) + 1);

			if (property.getRentPrice() != 0) {
				averageRentPrice = averageRentPrice + property.getRentPrice();
			}

			if (property.getAddress() != null && property.getAddress().getState().equals("IL")) {
				IllinoisTotal++;
			}
		}

		// Calculate total quantity
		propertyReport.setTotalQuantity(totalProperties);

		// Calculate the quantity of each type, 0 if there is no properties.
		propertyReport.setQuantityPerType(propertiesMap);

		// Calculate the average rent price (exclude the properties without rent price
		// or with rent price = 0)
		propertyReport.setAverageRentPrice(
				new BigDecimal(averageRentPrice / totalProperties).setScale(2, RoundingMode.HALF_UP).doubleValue());

		// Calculate the quantity of properties in the state of Illinois (IL)
		propertyReport.setIllinoisQuantity(IllinoisTotal);

		return propertyReport;
	}
}
