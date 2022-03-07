package com.propify.challenge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.propify.challenge.service.AddressService;
import com.propify.challenge.service.AlertService;
import com.propify.challenge.service.PropertyService;
import com.propify.challenge.service.impl.PropertyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    PropertyServiceImpl propertyService;

    PropertyService propertyMapper;

    AddressService addressMapper;

    AlertService alertService;

    // TODO: add at least 3 tests to the method propertyService.propertyReport()
    @Test
    public void testPropertyReport() {

    }

}
