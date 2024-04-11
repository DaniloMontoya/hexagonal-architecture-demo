package com.danilomontoya.hexagonalarchitecturedemo.application.model;

import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountStatus;
import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountType;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.InvalidValueException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.MandatoryValueException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.NotAllowedOperationException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 8:34 AM
 **/
@Getter
public class ProductModel {

    public static final String REQUIRED_ACCOUNT_NUMBER_MESSAGE = "Número de cuenta requerido";
    public static final String NOT_VALID_ACCOUNT_NUMBER_MESSAGE = "Número de cuenta no valido";
    public static final int ACCOUNT_NUM_DIGITS = 10;
    public static final String MIN_BALANCE_SAVING_ACCOUNT_MESSAGE = "La cuenta de ahorros no puede tener un saldo menor a $0 (cero)";
    private String id;
    private AccountType type;
    private String accountNumber;
    private AccountStatus status;
    private double balance;
    private boolean excludeGMF;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private String clientId;

    public ProductModel(String id, AccountType type, String accountNumber, AccountStatus status, double balance,
                        boolean excludeGMF, LocalDateTime creationDate, LocalDateTime modificationDate, String clientId){
        validateAccountNumber(accountNumber);
        validateSavingBalance(balance, type);
        this.accountNumber=accountNumber;
        this.id = generateId(id);
        this.type = type;
        this.status = status;
        this.balance = balance;
        this.excludeGMF = excludeGMF;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.clientId = clientId;
    }

    public void validateSavingBalance(double balance, AccountType type) {
        if(AccountType.SAVING.equals(type) && balance < 0){
            throw new NotAllowedOperationException(MIN_BALANCE_SAVING_ACCOUNT_MESSAGE);
        }
    }

    public void validateAccountNumber(String accountNumber) {
        if(null == accountNumber){
            throw new MandatoryValueException(REQUIRED_ACCOUNT_NUMBER_MESSAGE);
        }
        if(accountNumber.length() != ACCOUNT_NUM_DIGITS){
            throw new InvalidValueException(NOT_VALID_ACCOUNT_NUMBER_MESSAGE);
        }
    }

    private String generateId(String id) {
        if(null == id){
            return UUID.randomUUID().toString();
        }
        return id;
    }
}
