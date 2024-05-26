package com.bigproject.client_view.client.favouriteClient;

import com.bigproject.client_view.client.payload.FavouriteProductPayload;
import com.bigproject.client_view.entity.FavouriteProduct;

public interface FavouriteProductClient {


    FavouriteProductPayload findFavouriteProductByProductId(int id);

    FavouriteProductPayload addProductsToFavourites(Integer productId);

    void removeProductsToFavourites(int id);

    FavouriteProduct[] findFavouriteProducts();
}
