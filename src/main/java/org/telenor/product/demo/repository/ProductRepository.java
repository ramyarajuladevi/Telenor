package org.telenor.product.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.telenor.product.demo.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    // JPQL query to filter the products
    @Query("SELECT p FROM Product p "
            + "WHERE (:type IS NULL OR p.product_type = :type)"
            + "AND (:min_price IS NULL OR p.product_price >= :min_price)"
            + "AND (:max_price IS NULL OR p.product_price <= :max_price)"
            + "AND (:city IS NULL OR p.store_address LIKE CONCAT('%',CAST(:city java.lang.String),'%'))"
            + "AND (:property IS NULL OR p.product_properties LIKE CONCAT('%',CAST(:property java.lang.String),'%'))"
            + "AND (:property_color IS NULL OR p.product_properties LIKE CONCAT('%',CAST(:property_color java.lang.String),'%'))")
    List<Product> findPartialSearch1(String type, Double min_price, Double max_price, String city, String property, String property_color);
}
