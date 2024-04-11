package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.jpa.impl;

import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.jpa.ProductJpaRepository;
import com.danilomontoya.hexagonalarchitecturedemo.application.mapper.ProductMapper;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.ProductModel;
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
    public List<ProductModel> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(productMapper::entityToModel)
                .toList();
    }

    @Override
    public Optional<ProductModel> findById(String id) {
        return productJpaRepository.findById(id)
                .map(productMapper::entityToModel);
    }

    @Override
    public Optional<ProductModel> findByAccountNumber(String accountNumber) {
        return productJpaRepository.findByAccountNumber(accountNumber)
                .map(productMapper::entityToModel);
    }

    @Override
    public ProductModel create(ProductModel productModel) {
        var entity = productMapper.modelToEntity(productModel);
        return productMapper.entityToModel(productJpaRepository.save(entity));
    }

    @Override
    public ProductModel update(ProductModel productModel) {
        return this.create(productModel);
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
