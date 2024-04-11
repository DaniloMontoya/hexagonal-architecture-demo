package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.jpa;

import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 6:51 PM
 **/
@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, String> {
    boolean existsByIdentificationNumber(String identificationNumber);
}
