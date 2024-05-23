package com.bigproject.catalogueservice.service;

import com.bigproject.catalogueservice.entity.Vendor;
import com.bigproject.catalogueservice.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultVendorService implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public Optional<Vendor> findVendorById(Integer vendorId) {
        return vendorRepository.findById(vendorId);
    }

    @Override
    public Optional<Vendor> findVendorByName(String vendorName) {
        Optional<Vendor> vendor =vendorRepository.findVendorByVendorName(vendorName);
        if (vendor.isEmpty()){
            return Optional.of(createVendor(vendorName));
        }
        return vendor;
    }


    @Override
    @Transactional
    public Vendor createVendor(String vendorName) {
           return vendorRepository.saveAndFlush(Vendor.builder().vendorName(vendorName).build());
    }


    @Override
    @Transactional
    public void updateVendor() {

    }

    @Override
    @Transactional
    public void deleteVendor() {

    }
}
