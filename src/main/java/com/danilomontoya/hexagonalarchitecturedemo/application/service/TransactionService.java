package com.danilomontoya.hexagonalarchitecturedemo.application.service;

import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.transaction.ConsignmentDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.transaction.TransactionDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.transaction.TransferAccountDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.transaction.WithdrawalDTO;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:31 PM
 **/
public interface TransactionService {
    TransactionDTO consignment(ConsignmentDTO consignmentDTO);

    TransactionDTO withdrawal(WithdrawalDTO withdrawalDTO);

    TransactionDTO transferBetweenAccounts(TransferAccountDTO transferAccountDTO);

    List<TransactionDTO> findAll();
}
