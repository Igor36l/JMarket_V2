package com.bigproject.client_view.controllers.payload;

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
