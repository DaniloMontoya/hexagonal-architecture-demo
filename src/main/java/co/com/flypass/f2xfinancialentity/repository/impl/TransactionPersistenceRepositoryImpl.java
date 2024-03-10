package co.com.flypass.f2xfinancialentity.repository.impl;

import co.com.flypass.f2xfinancialentity.mapper.TransactionMapper;
import co.com.flypass.f2xfinancialentity.model.TransactionModel;
import co.com.flypass.f2xfinancialentity.repository.TransactionRepository;
import co.com.flypass.f2xfinancialentity.repository.jpa.TransactionJpaRepository;
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
