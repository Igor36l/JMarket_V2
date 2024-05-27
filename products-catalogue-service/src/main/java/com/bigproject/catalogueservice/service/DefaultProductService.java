package com.bigproject.catalogueservice.service;

import com.bigproject.catalogueservice.entity.Vendor;
import com.bigproject.catalogueservice.repository.ProductRepository;
import com.bigproject.catalogueservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> findAllProducts(String filter) {
        if (filter != null || filter.isBlank()) {
            return this.productRepository.findAllByTitleLikeIgnoreCase("%" + filter + "%");
        } else {
            return this.productRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Product createProduct(String title, String details, String imageFileName, Vendor ownerProduct, Integer price) {
        Product product = Product.builder()
                .title(title).details(details).imageFileName(imageFileName)
                .ownerProduct(ownerProduct).price(price).build();
        return this.productRepository.save(product);
    }

    @Override
    public Optional<Product> findProduct(Integer productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    @Transactional
    public void updateProduct(Integer id, String title, String details) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
                    this.productRepository.save(product);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Transactional
    @Override
    public void updateProduct(Integer id, Double averageRating) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    if (product.getCountMarks() != null) {
                        double average = product.getAverageRating();
                        double newRating = (average*product.getCountMarks()+averageRating)/(product.getCountMarks()+1.0);
                        product.setAverageRating(newRating);
                        product.setCountMarks(product.getCountMarks()+1.0);
                    }else {
                        product.setAverageRating(averageRating);
                        product.setCountMarks(1.0);
                    }
                    this.productRepository.save(product);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }


}
