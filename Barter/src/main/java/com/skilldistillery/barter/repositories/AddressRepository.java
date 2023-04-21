package com.skilldistillery.barter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
	

}
