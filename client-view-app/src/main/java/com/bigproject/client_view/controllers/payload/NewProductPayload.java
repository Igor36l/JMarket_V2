package com.bigproject.client_view.controllers.payload;

public record NewProductPayload (
        String title,
        String details,
        String imageFileName,
        String ownerProduct){
}
