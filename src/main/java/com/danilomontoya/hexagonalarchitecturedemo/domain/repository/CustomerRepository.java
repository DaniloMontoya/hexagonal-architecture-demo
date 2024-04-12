package com.danilomontoya.hexagonalarchitecturedemo.domain.repository;

import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 7:18 PM
 **/
public interface CustomerRepository {
    Optional<Customer> getClient(String id);
    List<Customer> getAllClients();
    Customer createClient(Customer client);
    Customer updateClient(Customer client);
    void deleteClient(String id);
    boolean existClient(String id);

    boolean existClientByDocumentNumber(String identificationNumber);
}
