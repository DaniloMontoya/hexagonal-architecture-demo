package com.danilomontoya.hexagonalarchitecturedemo.domain.repository;

import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.TransactionModel;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 1:31 PM
 **/
public interface TransactionRepository {
    TransactionModel save(TransactionModel transactionModel);

    List<TransactionModel> findAll();
}
