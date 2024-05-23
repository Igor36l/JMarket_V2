package com.bigproject.catalogueservice.repository;

import com.bigproject.catalogueservice.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    Optional<Vendor> findVendorByVendorName(String name);
}
