package com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.transaction;

import lombok.Data;


/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:30 PM
 **/
@Data
public class WithdrawalDTO {
    private String sourceAccount;
    private double amount;
}
