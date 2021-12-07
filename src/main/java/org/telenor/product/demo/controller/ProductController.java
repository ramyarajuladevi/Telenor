package org.telenor.product.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.telenor.product.demo.model.Product;
import org.telenor.product.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(value = "type", required =  false) String type,
            @RequestParam(value = "min_price", required =  false) Double min_price,
            @RequestParam(value = "max_price", required = false) Double max_price,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "property", required = false) String property,
            @RequestParam(value = "property:color", required = false) String property_color,
            @RequestParam(value = "property:gb_limit_min", required = false) Integer property_gb_limit_min,
            @RequestParam(value = "property:gb_limit_max", required = false) Integer property_gb_limit_max
    ) {
        List<Product> products = productService
                .getProducts(type, min_price, max_price, city, property, property_color, property_gb_limit_min, property_gb_limit_max);
        return products;
    }

}
