package com.domain.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repos.ProductRepo;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            return null;
        }
        return product.get();
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }

  

    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID: " + productId + "not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    public Product findByProductName(String name){
        return productRepo.findProductByName(name);
    }

    public List<Product> findByProductNameLike(String name){
        return productRepo.findProductByNameLike("%"+name+"%");
    }

    public List<Product> findByCategory(Long categoryId){
       return productRepo.findProductByCatergory(categoryId); 
    }

    public List<Product> findBySupplier(Long supplierId){
        Supplier supplier = supplierService.findOne(supplierId);
        if (supplier == null) {
            return new ArrayList<Product>();
        }
        return productRepo.findProductBySupplier(supplier);
    }
}
