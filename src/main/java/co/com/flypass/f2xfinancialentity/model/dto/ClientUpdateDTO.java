package co.com.flypass.f2xfinancialentity.model.dto;

import co.com.flypass.f2xfinancialentity.enums.IdentificationType;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 7:02 PM
 **/
@Data
public class ClientUpdateDTO {
    private String id;
    private IdentificationType identificationType;
    private String identificationNumber;
    private String name;
    private String lastname;
    private String email;
    private LocalDate birthday;
}
