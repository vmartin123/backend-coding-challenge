package com.propify.challenge.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.service.impl.PropertyServiceImpl;

@RestController
@RequestMapping("/properties")
public class PropertyController {

	@Autowired
	PropertyServiceImpl propertyService;

	// API endpoints for CRUD operations on entities of type Property
	@PostMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void insert(@RequestBody Property property) {
		propertyService.insert(property);
	}

	@GetMapping(value = "/search", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public Collection<Property> search(@RequestParam(name = "minRentPrice", required = false) String minRentPrice,
			@RequestParam(name = "maxRentPrice", required = false) String maxRentPrice) {
		return propertyService.search(minRentPrice, maxRentPrice);
	}

	public Property findById(Long id) {
		return propertyService.findById(id);
	}

	public void update(Property property) {
		// TODO: Property attributes must be validated
		propertyService.update(property);
	}

	public void delete(Long id) {
		propertyService.delete(id);
	}

	public PropertyReport report() {
		return propertyService.propertyReport();
	}
}
