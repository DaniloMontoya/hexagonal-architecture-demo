package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.jpa.impl;

import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.jpa.ProductJpaRepository;
import com.danilomontoya.hexagonalarchitecturedemo.application.mapper.ProductMapper;
import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Product;
import com.danilomontoya.hexagonalarchitecturedemo.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 10:32 AM
 **/
@Repository
@RequiredArgsConstructor
public class ProductPersistenceRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(productMapper::entityToModel)
                .toList();
    }

    @Override
    public Optional<Product> findById(String id) {
        return productJpaRepository.findById(id)
                .map(productMapper::entityToModel);
    }

    @Override
    public Optional<Product> findByAccountNumber(String accountNumber) {
        return productJpaRepository.findByAccountNumber(accountNumber)
                .map(productMapper::entityToModel);
    }

    @Override
    public Product create(Product product) {
        var entity = productMapper.modelToEntity(product);
        return productMapper.entityToModel(productJpaRepository.save(entity));
    }

    @Override
    public Product update(Product product) {
        return this.create(product);
    }

    @Override
    public boolean existsById(String id) {
        return productJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByClientId(String clientId) {
        return productJpaRepository.existsByClientId(clientId);
    }
}
