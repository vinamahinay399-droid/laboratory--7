package com.example.laboratory7;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private Map<Long, Product> productDB = new HashMap<>();
    private Long nextId = 4L;

    public ProductService() {
        productDB.put(1L, new Product(1L, "Laptop Pro", 55000.0));
        productDB.put(2L, new Product(2L, "Gaming Mouse", 1500.0));
        productDB.put(3L, new Product(3L, "Mechanical Keyboard", 3200.0));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productDB.values());
    }

    public Product getProductById(Long id) {
        return productDB.get(id);
    }

    public Product createProduct(Product product) {
        product.setId(nextId++);
        productDB.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(Long id, Product updated) {
        if (productDB.containsKey(id)) {
            updated.setId(id);
            productDB.put(id, updated);
            return updated;
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        return productDB.remove(id) != null;
    }
      }
