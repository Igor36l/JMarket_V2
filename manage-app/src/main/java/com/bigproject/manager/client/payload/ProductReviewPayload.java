package com.bigproject.manager.client.payload;

public record ProductReviewPayload(Integer productId, Integer rating, String review) {
}
