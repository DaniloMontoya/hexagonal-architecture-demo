package com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.transaction;

import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:23 PM
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String id;
    private TransactionType type;
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private LocalDateTime transactionDate;
}
