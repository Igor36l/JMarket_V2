package com.bigproject.manager.controllers.payload;

import java.util.UUID;

public record NewProductReviewPayload(UUID id, int productId, int rating, String review) {
}
