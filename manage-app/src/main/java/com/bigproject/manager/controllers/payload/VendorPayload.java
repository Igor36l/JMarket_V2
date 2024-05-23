package com.bigproject.manager.controllers.payload;

import jakarta.persistence.*;

import java.util.List;

public record VendorPayload(
        Integer id,
        String vendorName,
        String vendorMail,
        String vendorPhoneNumber,
        String vendorAddress,
        String city,
        boolean isActive

) {
}
