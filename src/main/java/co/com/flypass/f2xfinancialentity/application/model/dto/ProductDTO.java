package co.com.flypass.f2xfinancialentity.application.model.dto;

import co.com.flypass.f2xfinancialentity.domain.enums.AccountStatus;
import co.com.flypass.f2xfinancialentity.domain.enums.AccountType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:08 AM
 **/
@Data
public class ProductDTO {
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
