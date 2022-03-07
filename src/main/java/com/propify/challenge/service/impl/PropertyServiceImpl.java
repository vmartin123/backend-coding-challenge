package com.propify.challenge.service.impl;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.repository.PropertyRepository;
import com.propify.challenge.service.AddressService;
import com.propify.challenge.service.AlertService;
import com.propify.challenge.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	PropertyService propertyService;

	@Autowired
	PropertyRepository propertyRepository;

	AddressService addressMapper;

	AlertService alertService;

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

	public Set<Property> search(String minRentPrice, String maxRentPrice) {
		return propertyService.search(minRentPrice, maxRentPrice);
	}

	public Property findById(Long id) {
		return propertyService.findById(id);
	}

	public void update(Property property) {
		propertyService.update(property);
		System.out.println("UPDATED: " + property.getId());
	}

	public void delete(Long id) {
		propertyService.delete(id);
		System.out.println("DELETED: " + id);

		alertService.sendPropertyDeletedAlert(id);
		// TODO: Sending the alert should be non-blocking (asynchronous)
		// Extra points for only sending the alert when/if the transaction is committed
	}

	public PropertyReport propertyReport() {
		var allProperties = propertyService.search(null, null);
		var propertyReport = new PropertyReport();

		// Calculate total quantity
		// propertyReport.totalQuantity =

		// Calculate the quantity of each type, 0 if there is no properties.
		// propertyReport.quantityPerType =

		// Calculate the average rent price (exclude the properties without rent price
		// or with rent price = 0)
		// propertyReport.averageRentPrice =

		// Calculate the quantity of properties in the state of Illinois (IL)
		// propertyReport.illinoisQuantity =

		return propertyReport;
	}
}
