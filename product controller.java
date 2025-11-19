package com.example.laboratory7;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts()); // 200 OK
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product product = service.getProductById(id);

        if (product == null) {
            return ResponseEntity.notFound().build(); // 404
        }

        return ResponseEntity.ok(product); // 200
    }

    // POST CREATE
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = service.createProduct(product);

        return ResponseEntity
                .created(URI.create("/api/products/" + created.getId())) // 201 Created
                .body(created);
    }

    // PUT UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updated) {

        Product result = service.updateProduct(id, updated);

        if (result == null) {
            return ResponseEntity.notFound().build(); // 404
        }

        return ResponseEntity.ok(result); // 200
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.deleteProduct(id);

        if (!deleted) {
            return ResponseEntity.notFound().build(); // 404
        }

        return ResponseEntity.ok().build(); // 200 OK
    }
                                           }
