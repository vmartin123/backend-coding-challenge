package com.propify.challenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.propify.challenge.model.Address;
import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyType;
import com.propify.challenge.repository.PropertyRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PropertyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PropertyRepository propertyRepository;

	@Test
	public void testSearch() throws Exception {
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

		List<Property> propertiesList = new ArrayList<>();
		propertiesList.add(property);

		Mockito.when(propertyRepository.findAll()).thenReturn(propertiesList);

		this.mockMvc.perform(get("/properties")).andExpect(status().isOk()).andExpect(jsonPath("$.[0].id").value(1))
				.andExpect(jsonPath("$.[0].type").value("SINGLE_FAMILY"))
				.andExpect(jsonPath("$.[0].rentPrice").value(1.11))
				.andExpect(jsonPath("$.[0].emailAddress").value("vic@gmail.com"))
				.andExpect(jsonPath("$.[0].code").value("A1")).andExpect(jsonPath("$.[0].address.id").value(1))
				.andExpect(jsonPath("$.[0].address.street").value("belgrano"))
				.andExpect(jsonPath("$.[0].address.city").value("chicago"))
				.andExpect(jsonPath("$.[0].address.state").value("IL"))
				.andExpect(jsonPath("$.[0].address.zip").value("1200"))
				.andExpect(jsonPath("$.[0].address.timezone").value("CST"));
	}
}
