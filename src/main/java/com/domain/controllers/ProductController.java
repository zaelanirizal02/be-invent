package com.domain.controllers;

import java.util.List;

// import org.hibernate.Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> save(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(product);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(product);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        productService.removeOne(id);
    }

    @PostMapping("{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId) {
        productService.addSupplier(supplier, productId);
    }

    @PostMapping("/search/name")
    public Product getProductByName(@RequestBody SearchData searchData){
        return productService.findByProductName(searchData.getSearchKey());
    }

    @PostMapping("/search/namelike")
    public List<Product> getProductByNameLike(@RequestBody SearchData searchData){
        return productService.findByProductNameLike(searchData.getSearchKey());
    }

    //cari by category id
    @GetMapping("/search/category/{categoryId}")
    public List<Product> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.findByCategory(categoryId);
    }

    //cari by supplier id
    @GetMapping("/search/supplier/{supplierId}")
    public List<Product> getProductBySupplier(@PathVariable("supplierId") Long supplierId){
        return productService.findBySupplier(supplierId);
    }
}
