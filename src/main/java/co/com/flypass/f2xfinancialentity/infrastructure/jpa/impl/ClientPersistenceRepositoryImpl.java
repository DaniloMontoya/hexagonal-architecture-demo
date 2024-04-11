package co.com.flypass.f2xfinancialentity.infrastructure.jpa.impl;

import co.com.flypass.f2xfinancialentity.application.mapper.ClientBuilder;
import co.com.flypass.f2xfinancialentity.application.model.ClientModel;
import co.com.flypass.f2xfinancialentity.domain.repository.ClientRepository;
import co.com.flypass.f2xfinancialentity.infrastructure.jpa.ClientJpaRepository;
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
public class ClientPersistenceRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientBuilder clientBuilder;

    @Override
    public Optional<ClientModel> getClient(String id) {
        return clientJpaRepository.findById(id)
                .map(clientBuilder::entityToModel);
    }

    @Override
    public List<ClientModel> getAllClients() {
        return clientJpaRepository.findAll()
                .stream()
                .map(clientBuilder::entityToModel)
                .toList();
    }

    @Override
    public ClientModel createClient(ClientModel client) {
        var clientEntity = clientBuilder.modelToEntity(client);
        return clientBuilder.entityToModel(clientJpaRepository.save(clientEntity));
    }

    @Override
    public ClientModel updateClient(ClientModel client) {
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
