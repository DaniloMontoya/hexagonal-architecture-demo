package co.com.flypass.j2xfinancialentity.entity;

import co.com.flypass.j2xfinancialentity.enums.IdentificationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 4:51 PM
 **/
@Entity
@Table(name = "CLIENT")
@Data
public class ClientEntity {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;

    private String identificationNumber;
    private String name;
    private String lastname;
    private String email;
    private LocalDate birthday;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
