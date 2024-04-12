package com.danilomontoya.hexagonalarchitecturedemo.model;

import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Product;
import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountStatus;
import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountType;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 4:01 PM
 **/
public class ProductBuilderTest {
    private String id = "1";
    private AccountType type = AccountType.SAVING;
    private String accountNumber = "5348218446";
    private AccountStatus status = AccountStatus.ACTIVE;
    private double balance = 0;
    private boolean excludeGMF = true;
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime modificationDate = LocalDateTime.now();
    private String clientId = "12345";

    public Product build() {
        return new Product(id,
                type,
                accountNumber,
                status,
                balance,
                excludeGMF,
                creationDate,
                modificationDate,
                clientId);
    }

    public ProductBuilderTest withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }
    public ProductBuilderTest withType(AccountType type) {
        this.type = type;
        return this;
    }

    public ProductBuilderTest withStatus(AccountStatus status) {
        this.status = status;
        return this;
    }

    public ProductBuilderTest withBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public ProductBuilderTest withExcludeGMF(boolean excludeGMF) {
        this.excludeGMF = excludeGMF;
        return this;
    }

    public ProductBuilderTest withCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ProductBuilderTest withModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }
}
