package com.bigproject.customerapp.client.payload;

public record NewProductReviewPayload(Integer productId, Integer rating, String review) {
}
