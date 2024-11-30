package com.luxury.wear.service.repository;

import com.luxury.wear.service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUserEmail(String email);
}
