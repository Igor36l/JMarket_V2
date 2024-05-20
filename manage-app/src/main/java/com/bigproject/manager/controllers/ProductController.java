package com.bigproject.manager.controllers;

import com.bigproject.manager.client.reviewClient.exception.BadRequestException;
import com.bigproject.manager.client.favouriteClient.FavouriteProductClient;
import com.bigproject.manager.client.productClient.ProductRestClient;
import com.bigproject.manager.client.reviewClient.ReviewProductsClient;
import com.bigproject.manager.controllers.payload.NewProductReviewPayload;
import com.bigproject.manager.controllers.payload.UpdateProductPayload;
import com.bigproject.manager.entity.Product;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
@Slf4j
public class ProductController {

    private final ProductRestClient productRestClient;

    private final FavouriteProductClient favouriteProductClient;

    private final MessageSource messageSource;

    private final ReviewProductsClient productReviewsClient;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productRestClient.findProduct(productId).orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @PostMapping("add-to-favourites")
    public String addProductToFavourites(@ModelAttribute("product") Product product) {
        this.favouriteProductClient.addProductsToFavourites(product.id());
        return "redirect:/catalogue/products/%d".formatted(product.id());

    }

    @PostMapping("create-review")
    public String createReview(@PathVariable("productId") int id,
                               NewProductReviewPayload payload,
                               Model model) {
        this.productReviewsClient.createProductReview(id, payload.rating(), payload.review());
        model.addAttribute("inFavourite", false);
        model.addAttribute("payload", payload);
        return "redirect:/catalogue/products/%d".formatted(id);
    }

    @PostMapping("remove-from-favourites")
    public String removeProductToFavourites(@ModelAttribute("product") Product product) {
        this.favouriteProductClient.removeProductsToFavourites(product.id());
        return "redirect:/catalogue/products/%d".formatted(product.id());

    }

    @GetMapping
    public String getProduct(@PathVariable("productId") int productId, Model model) {
        model.addAttribute("reviews", productReviewsClient.findProductReviewsByProductId(productId));
        model.addAttribute("inFavourite", false);
        if (this.favouriteProductClient.findFavouriteProductByProductId(productId) != null) {
            model.addAttribute("inFavourite", true);
        }
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute(value = "product", binding = false) Product product,
                                UpdateProductPayload payload, Model model) {
        try {
            this.productRestClient.updateProduct(product.id(), payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/edit";
        }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productRestClient.deleteProduct(product.id());
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response, Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", this.messageSource.getMessage(exception.getMessage(), new Object[0], exception.getMessage(), locale));

        return "/errors/404";
    }

}
