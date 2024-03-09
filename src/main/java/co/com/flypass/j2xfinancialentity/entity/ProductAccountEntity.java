package co.com.flypass.j2xfinancialentity.entity;

import co.com.flypass.j2xfinancialentity.enums.AccountStatus;
import co.com.flypass.j2xfinancialentity.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 4:54 PM
 **/
@Data
@Entity
@Table(name = "PRODUCT")
public class ProductAccountEntity {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private double balance;
    private boolean excludeGMF;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
}
