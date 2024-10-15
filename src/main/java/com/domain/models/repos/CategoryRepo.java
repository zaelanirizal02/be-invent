package com.domain.models.repos;

import com.domain.models.entities.Category;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {

    Page<Category> findByNameContains(String name, Pageable pageable);

    Category save(Category category);

    Optional<Category> findById(Long id);

    Iterable<Category> findAll();

    void deleteById(Long id);

    Iterable<Category> saveAll(Iterable<Category> categories);
}
