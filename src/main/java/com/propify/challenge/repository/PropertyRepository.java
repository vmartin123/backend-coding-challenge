package com.propify.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propify.challenge.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>, PropertyRepositoryCustom {

}
