package com.bigproject.catalogueservice.repository;

import com.bigproject.catalogueservice.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(name = "Product.findAllByTitleLikeIgnoringCase", nativeQuery = true)
    Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
}
