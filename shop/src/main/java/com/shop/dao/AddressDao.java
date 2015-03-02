package com.shop.dao;

import java.util.List;

public interface AddressDao {

	// create new address
	Address createAddress(Address address);

	// get all existing addresses
	List<Address> getAllAddresses();

	// get address by id
	Address getAddressById(int id);

	// updates address by address and returns updated address id
	int updateAddress(Address address);

	// deletes address by address and returns deleted address id
	int deleteAddress(Address address);

	// create new address and return new created id
	int createAddressGetIdCreated(Address address);
}