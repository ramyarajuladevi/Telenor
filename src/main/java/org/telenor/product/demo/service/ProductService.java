package org.telenor.product.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telenor.product.demo.model.Product;
import org.telenor.product.demo.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final Integer MIN_LIMIT = 0;
    private final Integer MAX_LIMIT = 1000;

    public List<Product> getProducts(String type, Double min_price, Double max_price,
                                     String city, String property, String property_color,
                                     Integer property_gb_limit_min, Integer property_gb_limit_max) {
        List<Product> products = productRepository.findPartialSearch1(type, min_price, max_price, city, property, property_color);

        /* Filter products based on gb limits
           The below logic should execute only when gb limits received as parameters
         */
        if (property_gb_limit_min != null || property_gb_limit_max != null) {
            Integer product_limit, min_limit, max_limit;
            List<Product> gb_products = new ArrayList<>();

            // Set the limits(Min and Max) to be used in the checks

            // Minimum limit
            if (property_gb_limit_min != null) {
                min_limit = property_gb_limit_min;
            } else {
                min_limit = MIN_LIMIT;
            }

            // Maximum limit
            if (property_gb_limit_max != null ) {
                max_limit = property_gb_limit_max;
            } else {
                max_limit = MAX_LIMIT;
            }

            // Loop through the partially filtered products and filter it by gb_limits
            for (Product product: products) {
                String[] splitProperties = product.getProduct_properties().split(":");
                if (splitProperties[0].contains("gb_limit")) {
                    product_limit = Integer.parseInt(splitProperties[1]);
                    if (product_limit >= min_limit && product_limit <= max_limit) {
                        gb_products.add(product);
                    }
                }
            }
            // If gb limits are set, return filtered products by gb limits
            return gb_products;
        }

        // If gb limits are not set, return partially filtered products directly received from the repository
        return products;
    }
}
