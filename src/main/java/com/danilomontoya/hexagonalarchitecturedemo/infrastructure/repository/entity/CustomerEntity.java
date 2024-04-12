package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.entity;

import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Customer;
import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.IdentificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
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

    public CustomerEntity(String id){
        this.id = id;
    }

    public CustomerEntity(Customer customer){
        this.id = customer.getId();
    }

    public Customer toCustomer(){
        // TODO: 11/04/24 Retornar el dominio customer.
//        return new Customer()
        return null;
    }
}
