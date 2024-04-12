package com.danilomontoya.hexagonalarchitecturedemo.application.service.impl;

import com.danilomontoya.hexagonalarchitecturedemo.application.service.CustomerService;
import com.danilomontoya.hexagonalarchitecturedemo.application.service.validator.ValidateProductsService;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.DuplicityValueException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.MandatoryValueException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.NotAllowedOperationException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.NotFindException;
import com.danilomontoya.hexagonalarchitecturedemo.application.mapper.ClientMapper;
import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Customer;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerCreateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerUpdateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.domain.repository.CustomerRepository;
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
public class CustomerServiceImpl implements CustomerService {
    public static final String REQUIRED_CLIENT_ID_MESSAGE = "Id del cliente requerido";
    public static final String CLIENT_HAVE_PRODUCTS = "El cliente tiene productos con la entidad";
    public static final String NOT_FIND_CLIENT_MESSAGE = "No existe cliente con el id:";
    public static final String EXIST_CLIENT_WITH_DOCUMENT_NUMBER = "Ya existe un cliente con el número de documento";
    private final CustomerRepository customerRepository;
    private final ClientMapper clientMapper;
    private final ValidateProductsService validateProductsService;

    @Override
    public CustomerDTO create(CustomerCreateDTO customerCreateDTO) {
        log.debug("Creating client... {}", customerCreateDTO);
        Customer customer = clientMapper.createDtoToModel(customerCreateDTO);
        boolean exist = customerRepository.existClientByDocumentNumber(customer.getIdentificationNumber());
        if(exist){
            log.error("Exist client with identification: {}", customer.getIdentificationNumber());
            throw new DuplicityValueException(EXIST_CLIENT_WITH_DOCUMENT_NUMBER);
        }
        customer.setCreationDate(LocalDateTime.now());
        customer.setModificationDate(LocalDateTime.now());
        var savedClient = customerRepository.createClient(customer);
        log.info("Client created {}", savedClient);
        return clientMapper.modelToDto(savedClient);
    }

    @Override
    public CustomerDTO update(CustomerUpdateDTO customerUpdateDTO) {
        log.debug("Updating client... {}", customerUpdateDTO);
        var savedClient = customerRepository.getClient(customerUpdateDTO.getId())
                .orElseThrow(()-> new NotFindException(NOT_FIND_CLIENT_MESSAGE + customerUpdateDTO.getId()));
        log.debug("Find client {}", savedClient);

        Customer customer = clientMapper.updateDtoToModel(customerUpdateDTO);
        customer.setModificationDate(LocalDateTime.now());
        customer.setCreationDate(savedClient.getCreationDate());
        var updatedClient = customerRepository.updateClient(customer);
        log.info("Updated client {}", updatedClient);
        return clientMapper.modelToDto(updatedClient);
    }

    @Override
    public List<CustomerDTO> findAll() {
        log.debug("Find all clients...");
        return customerRepository.getAllClients().stream()
                .map(clientMapper::modelToDto)
                .toList();
    }

    @Override
    public CustomerDTO findClientById(String id) {
        if(null == id){
            throw new MandatoryValueException(REQUIRED_CLIENT_ID_MESSAGE);
        }
        return customerRepository.getClient(id)
                .map(clientMapper::modelToDto)
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
        customerRepository.deleteClient(id);
        log.info("Client deleted... {}", id);
    }
}
