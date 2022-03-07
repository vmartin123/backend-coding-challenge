package com.propify.challenge;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyType;
import com.propify.challenge.service.PropertyService;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@MybatisTest
@SpringJUnitConfig
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DatabaseSetup("PropertyMapperTest.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class PropertyMapperTest {

    @Autowired
    PropertyService propertyMapper;

    @Test
    public void testInsert() {
        var property = new Property();
        property.setRentPrice(3000.99);
        property.setType(PropertyType.MULTI_FAMILY);

        propertyMapper.insert(property);

        // TODO: add assertions
    }

    @Test
    public void testFindById() {
        var property = propertyMapper.findById(1l);

        assert property != null;
        // TODO: add assertions
    }

    // TODO: Add more tests
}
