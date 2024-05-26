package com.bigproject.catalogueservice.controller;

import com.bigproject.catalogueservice.controller.payload.NewProductPayload;
import com.bigproject.catalogueservice.entity.Product;
import com.bigproject.catalogueservice.entity.Vendor;
import com.bigproject.catalogueservice.service.ProductService;
import com.bigproject.catalogueservice.service.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products")
public class ProductsRestController {

    private final ProductService productService;

    private final VendorService vendorService;


    @GetMapping
    public Iterable<Product> findProducts(@RequestParam(name = "filter", required = false) String filter) {
        return this.productService.findAllProducts(filter);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody NewProductPayload payload,
                                           BindingResult bindingResult, UriComponentsBuilder uriBuilder)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Optional<Vendor> vendor = vendorService.findVendorByName(payload.ownerProduct());
            Product product = this.productService.createProduct(payload.title(), payload.details(), payload.imageFileName(), vendor.get(), payload.price());
            return ResponseEntity
                    .created(uriBuilder
                            .replacePath("catalogue-api/products/{productId}")
                            .build(Map.of("productId", product.getId())))
                    .body(product);
        }

    }

}
