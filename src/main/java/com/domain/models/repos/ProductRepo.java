package com.domain.models.repos;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;


public interface ProductRepo extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name =  :name")
    public Product findProductByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@Param("name") String name);

    @Query("SELECT p From Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCatergory(@Param("categoryId")Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@Param("supplier") Supplier supplier);
}
