package com.danilomontoya.hexagonalarchitecturedemo.application.service.impl;

import com.danilomontoya.hexagonalarchitecturedemo.application.service.ClientService;
import com.danilomontoya.hexagonalarchitecturedemo.application.service.ValidateProductsService;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.DuplicityValueException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.MandatoryValueException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.NotAllowedOperationException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.NotFindException;
import com.danilomontoya.hexagonalarchitecturedemo.application.mapper.ClientBuilder;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.ClientModel;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.ClientCreateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.ClientDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.ClientUpdateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 7:31 PM
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    public static final String REQUIRED_CLIENT_ID_MESSAGE = "Id del cliente requerido";
    public static final String CLIENT_HAVE_PRODUCTS = "El cliente tiene productos con la entidad";
    public static final String NOT_FIND_CLIENT_MESSAGE = "No existe cliente con el id:";
    public static final String EXIST_CLIENT_WITH_DOCUMENT_NUMBER = "Ya existe un cliente con el número de documento";
    private final ClientRepository clientRepository;
    private final ClientBuilder clientBuilder;
    private final ValidateProductsService validateProductsService;

    @Override
    public ClientDTO create(ClientCreateDTO clientCreateDTO) {
        log.debug("Creating client... {}", clientCreateDTO);
        ClientModel clientModel = clientBuilder.createDtoToModel(clientCreateDTO);
        boolean exist = clientRepository.existClientByDocumentNumber(clientModel.getIdentificationNumber());
        if(exist){
            log.error("Exist client with identification: {}", clientModel.getIdentificationNumber());
            throw new DuplicityValueException(EXIST_CLIENT_WITH_DOCUMENT_NUMBER);
        }
        clientModel.setCreationDate(LocalDateTime.now());
        clientModel.setModificationDate(LocalDateTime.now());
        var savedClient = clientRepository.createClient(clientModel);
        log.info("Client created {}", savedClient);
        return clientBuilder.modelToDto(savedClient);
    }

    @Override
    public ClientDTO update(ClientUpdateDTO clientUpdateDTO) {
        log.debug("Updating client... {}", clientUpdateDTO);
        var savedClient = clientRepository.getClient(clientUpdateDTO.getId())
                .orElseThrow(()-> new NotFindException(NOT_FIND_CLIENT_MESSAGE + clientUpdateDTO.getId()));
        log.debug("Find client {}", savedClient);

        ClientModel clientModel = clientBuilder.updateDtoToModel(clientUpdateDTO);
        clientModel.setModificationDate(LocalDateTime.now());
        clientModel.setCreationDate(savedClient.getCreationDate());
        var updatedClient = clientRepository.updateClient(clientModel);
        log.info("Updated client {}", updatedClient);
        return clientBuilder.modelToDto(updatedClient);
    }

    @Override
    public List<ClientDTO> findAll() {
        log.debug("Find all clients...");
        return clientRepository.getAllClients().stream()
                .map(clientBuilder::modelToDto)
                .toList();
    }

    @Override
    public ClientDTO findClientById(String id) {
        if(null == id){
            throw new MandatoryValueException(REQUIRED_CLIENT_ID_MESSAGE);
        }
        return clientRepository.getClient(id)
                .map(clientBuilder::modelToDto)
                .orElseThrow(()-> new NotFindException(NOT_FIND_CLIENT_MESSAGE + id));
    }

    @Override
    public void delete(String id) {
        log.debug("Deleting client... {}", id);
        findClientById(id);
        boolean isHaveProducts = validateProductsService.existProductsByClientId(id);
        if(isHaveProducts){
            log.error("Error to delete client (have products)");
            throw new NotAllowedOperationException(CLIENT_HAVE_PRODUCTS);
        }
        clientRepository.deleteClient(id);
        log.info("Client deleted... {}", id);
    }
}
