package com.bigproject.client_view.entity;

import com.bigproject.client_view.controllers.payload.VendorPayload;

public record Product(int id, String title, String details, String imageFileName,
                      VendorPayload ownerProduct, Double averageRating) {
}
