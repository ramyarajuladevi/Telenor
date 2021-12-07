package org.telenor.product.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* Type of the product */
    private String product_type;

    /* properties of the product */
    private String product_properties;

    /* Cost of the product */
    private Double product_price;

    /* store address of the product */
    private String store_address;


}
