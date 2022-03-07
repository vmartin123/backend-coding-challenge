package com.propify.challenge.service;

import java.util.Set;

import com.propify.challenge.model.Address;

public interface AddressService {

	public void insert(Address address);

	public Set<Address> search();

	public Address findById(Integer id);

}
