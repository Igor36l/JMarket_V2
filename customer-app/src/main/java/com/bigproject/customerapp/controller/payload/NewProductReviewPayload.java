package com.bigproject.customerapp.controller.payload;

public record NewProductReviewPayload(Integer productId, Integer rating, String review) {
}
