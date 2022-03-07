package com.propify.challenge.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(value = "/{propertyId}", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public Property findById(@PathVariable("propertyId") Long id) {
		return propertyService.findById(id);
	}

	@PutMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Property property) {
		propertyService.update(property);
	}

	@DeleteMapping(value = "/{propertyId}", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("propertyId") Long id) {
		propertyService.delete(id);
	}

	public PropertyReport report() {
		return propertyService.propertyReport();
	}
}
