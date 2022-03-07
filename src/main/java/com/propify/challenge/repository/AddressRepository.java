package com.propify.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propify.challenge.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
