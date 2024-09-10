package com.domain.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Supplier;
import com.domain.models.repos.SupplierRepo;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id){
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if(!supplier.isPresent()){
            return null;
        }
        return supplier.get();
    }

    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }

    //menggunakan jpa custom
    public Supplier findByEmail(String email){
        return supplierRepo.findByEmail(email);
    }

    //menggunakan jpa custom untuk mencari name
    public List<Supplier> findByName (String name){
        return supplierRepo.findByNameContains(name);
    }

     //menggunakan jpa custom untuk mencari name
     public List<Supplier> findByNameStartWith (String prefix){
        return supplierRepo.findByNameStartingWith(prefix);
    }

    public List<Supplier> findByAddress (String address){
        return supplierRepo.findByAddressContains(address);
    }

}
