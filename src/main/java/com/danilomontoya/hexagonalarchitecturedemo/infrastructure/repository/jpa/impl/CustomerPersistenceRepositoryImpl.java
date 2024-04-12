package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.jpa.impl;

import com.danilomontoya.hexagonalarchitecturedemo.application.mapper.ClientMapper;
import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Customer;
import com.danilomontoya.hexagonalarchitecturedemo.domain.repository.CustomerRepository;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.jpa.ClientJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 8:15 PM
 **/
@RequiredArgsConstructor
@Repository
public class CustomerPersistenceRepositoryImpl implements CustomerRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientMapper clientMapper;

    @Override
    public Optional<Customer> getClient(String id) {
        return clientJpaRepository.findById(id)
                .map(clientMapper::entityToModel);
    }

    @Override
    public List<Customer> getAllClients() {
        return clientJpaRepository.findAll()
                .stream()
                .map(clientMapper::entityToModel)
                .toList();
    }

    @Override
    public Customer createClient(Customer client) {
        var clientEntity = clientMapper.modelToEntity(client);
        return clientMapper.entityToModel(clientJpaRepository.save(clientEntity));
    }

    @Override
    public Customer updateClient(Customer client) {
        return this.createClient(client);
    }

    @Override
    public void deleteClient(String id) {
        clientJpaRepository.deleteById(id);
    }

    @Override
    public boolean existClient(String id) {
        return clientJpaRepository.existsById(id);
    }

    @Override
    public boolean existClientByDocumentNumber(String identificationNumber) {
        return clientJpaRepository.existsByIdentificationNumber(identificationNumber);
    }
}
