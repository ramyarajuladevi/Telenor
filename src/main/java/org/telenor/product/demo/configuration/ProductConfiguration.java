package org.telenor.product.demo.configuration;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.telenor.product.demo.model.Product;
import org.telenor.product.demo.repository.ProductRepository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Configuration
public class ProductConfiguration {
    @Autowired
    private ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void InitConfiguration() throws IOException {
        Reader in = new FileReader("src/main/resources/product_configuration.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String product_type = record.get("Product type");
            String product_properties = record.get("Product properties");
            // Expecting source file should have double values, so not handling error case
            Double product_price = Double.parseDouble(record.get("Price"));
            String store_address = record.get("Store address");
            Product p = Product.builder()
                    .product_type(product_type)
                    .product_properties(product_properties)
                    .product_price(product_price)
                    .store_address(store_address)
                    .build();
            productRepository.save(p);
        }
    }

}
