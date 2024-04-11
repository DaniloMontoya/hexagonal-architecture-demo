package com.danilomontoya.hexagonalarchitecturedemo.application.model.dto;

import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountStatus;
import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:13 AM
 **/
@Data
public class ProductUpdateDTO {
    private String id;
    private AccountType type;
    private String accountNumber;
    private AccountStatus status;
    private double balance;
    private boolean excludeGMF;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private String clientId;
}
