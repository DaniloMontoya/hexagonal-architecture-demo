package com.danilomontoya.hexagonalarchitecturedemo.domain.entity;


import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 5:00 PM
 **/
@Data
@Entity
@Table(name = "TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private LocalDateTime transactionDate;
}
