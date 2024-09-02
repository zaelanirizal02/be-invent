package com.domain.models.repos;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Product;

import jakarta.websocket.server.PathParam;

public interface ProductRepo extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name =  :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p From Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCatergory(@PathParam("categoryId")Long categoryId);
}
