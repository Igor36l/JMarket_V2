package com.bigproject.catalogueservice.service;

import com.bigproject.catalogueservice.entity.Vendor;

import java.util.Optional;

public interface VendorService {
    Optional<Vendor> findVendorById(Integer vendorId);
    Optional<Vendor> findVendorByName(String vendorName);
    Vendor createVendor(String vendorName);
    void  updateVendor();
    void deleteVendor();

}
