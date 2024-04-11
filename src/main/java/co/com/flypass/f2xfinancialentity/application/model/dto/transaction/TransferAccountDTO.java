package co.com.flypass.f2xfinancialentity.application.model.dto.transaction;

import lombok.Data;


/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:34 PM
 **/
@Data
public class TransferAccountDTO {
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
}
