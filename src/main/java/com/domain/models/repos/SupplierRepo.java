package com.domain.models.repos;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

    //menggunakan jpa custom automatic
    Supplier findByEmail(String email);

    //menggunakan jpa custom untuk mencari name secara bebas
    List <Supplier> findByNameContains(String name); 

    List <Supplier> findByNameStartingWith (String prefix);

    List <Supplier> findByAddressContains (String address);

} 