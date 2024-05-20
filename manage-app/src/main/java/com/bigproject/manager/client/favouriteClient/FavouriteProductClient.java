package com.bigproject.manager.client.favouriteClient;

import com.bigproject.manager.client.payload.FavouriteProductPayload;
import com.bigproject.manager.entity.FavouriteProduct;

public interface FavouriteProductClient {


    FavouriteProductPayload findFavouriteProductByProductId(int id);

    FavouriteProductPayload addProductsToFavourites(Integer productId);

    void removeProductsToFavourites(int id);

    FavouriteProduct[] findFavouriteProducts();
}
