package com.bigproject.manager.client.reviewClient;

import com.bigproject.manager.controllers.payload.NewProductReviewPayload;

public interface ReviewProductsClient {

    NewProductReviewPayload createProductReview(Integer productId, Integer rating, String review);

    NewProductReviewPayload[] findProductReviewsByProductId(int productId);
}
