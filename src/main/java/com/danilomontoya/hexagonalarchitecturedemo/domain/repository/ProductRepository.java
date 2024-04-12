package com.danilomontoya.hexagonalarchitecturedemo.domain.repository;

import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 8:53 AM
 **/
public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findById(String id);

    Optional<Product> findByAccountNumber(String accountNumber);

    Product create(Product product);
    Product update(Product product);

    boolean existsById(String id);

    boolean existsByClientId(String clientId);
}
