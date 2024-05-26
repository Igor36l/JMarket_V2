package com.bigproject.client_view.controllers.payload;

import java.util.UUID;

public record NewProductReviewPayload(UUID id, int productId, int rating, String review) {
}
