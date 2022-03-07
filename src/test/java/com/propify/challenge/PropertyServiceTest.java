package com.propify.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.propify.challenge.model.Address;
import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.model.PropertyType;
import com.propify.challenge.repository.PropertyRepository;
import com.propify.challenge.service.PropertyService;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyServiceTest {

	@Autowired
	PropertyService propertyService;

	@MockBean
	PropertyRepository propertyRepository;

	@Test
	public void testPropertyReport() {
		Address address = new Address();
		address.setId(1l);
		address.setStreet("belgrano");
		address.setCity("chicago");
		address.setState("IL");
		address.setZip("1200");
		address.setTimezone("CST");

		Property property = new Property();
		property.setId(1l);
		property.setType(PropertyType.SINGLE_FAMILY);
		property.setRentPrice(1.11);
		property.setAddress(address);
		property.setEmailAddress("vic@gmail.com");
		property.setCode("A1");

		Set<Property> properties = new HashSet<Property>();
		properties.add(property);

		Mockito.when(propertyRepository.search(null, null)).thenReturn(properties);

		PropertyReport propertyReport = propertyService.propertyReport();
		assertEquals(1, propertyReport.getTotalQuantity());
		assertEquals(1, propertyReport.getQuantityPerType().get(PropertyType.SINGLE_FAMILY));
		assertEquals(0, propertyReport.getQuantityPerType().get(PropertyType.MULTI_FAMILY));
		assertEquals(0, propertyReport.getQuantityPerType().get(PropertyType.CONDOMINIUM));
		assertEquals(0, propertyReport.getQuantityPerType().get(PropertyType.TOWN_HOUSE));
		assertEquals(1.11, propertyReport.getAverageRentPrice());
		assertEquals(1, propertyReport.getIllinoisQuantity());
	}

}
