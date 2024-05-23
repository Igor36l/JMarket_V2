package com.bigproject.manager.entity;

import com.bigproject.manager.controllers.payload.VendorPayload;

public record Product(int id, String title, String details, String imageFileName, VendorPayload ownerProduct) {
}
