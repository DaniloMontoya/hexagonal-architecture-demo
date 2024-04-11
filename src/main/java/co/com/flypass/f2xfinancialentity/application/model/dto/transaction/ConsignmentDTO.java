package co.com.flypass.f2xfinancialentity.application.model.dto.transaction;

import lombok.Data;


/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:25 PM
 **/
@Data
public class ConsignmentDTO {
    private String destinationAccount;
    private double amount;
}
