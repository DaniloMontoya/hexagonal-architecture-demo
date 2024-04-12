package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.entity;

import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountStatus;
import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountType;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 4:54 PM
 **/
@Data
@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
public class ProductAccountEntity {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private double balance;
    private boolean excludeGMF;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private CustomerEntity client;
}
