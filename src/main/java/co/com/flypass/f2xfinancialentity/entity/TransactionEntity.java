package co.com.flypass.f2xfinancialentity.entity;


import co.com.flypass.f2xfinancialentity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 5:00 PM
 **/
@Data
@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {
    @Id

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
