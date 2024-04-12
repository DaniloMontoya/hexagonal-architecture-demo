package com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.product;

import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountType;
import lombok.Data;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:11 AM
 **/
@Data
public class ProductCreateDTO {
    private AccountType type;
    private String clientId;
}
