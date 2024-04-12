package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.jpa.impl;

import com.danilomontoya.hexagonalarchitecturedemo.application.mapper.TransactionMapper;
import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.TransactionModel;
import com.danilomontoya.hexagonalarchitecturedemo.domain.repository.TransactionRepository;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.jpa.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 1:36 PM
 **/
@Repository
@RequiredArgsConstructor
public class TransactionPersistenceRepositoryImpl implements TransactionRepository {
    private final TransactionJpaRepository transactionJpaRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionModel save(TransactionModel transactionModel) {
        var entity = transactionMapper.modelToEntity(transactionModel);
        var savedEntity = transactionJpaRepository.save(entity);
        return transactionMapper.entityToModel(savedEntity);
    }

    @Override
    public List<TransactionModel> findAll() {
        return transactionJpaRepository.findAll()
                .stream()
                .map(transactionMapper::entityToModel)
                .toList();
    }
}
