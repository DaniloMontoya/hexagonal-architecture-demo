package com.danilomontoya.hexagonalarchitecturedemo.application.mapper;

import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.entity.CustomerEntity;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.MandatoryValueException;
import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Customer;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerCreateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerUpdateDTO;
import org.springframework.stereotype.Component;


/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 7:37 PM
 **/
@Component
public class ClientMapper {

    public static final String CLIENT_CREATE_DTO_ES_NULL = "ClientCreateDTO es nula";
    public static final String CLIENT_UPDATE_DTO_IS_NULL = "ClientUpdateDTO es nula";
    public static final String CLIENT_MODEL_IS_NULL = "ClientModel es nula";
    public static final String CLIENT_ENTITY_IS_NULL = "ClientEntity es nula";

    public Customer createDtoToModel(CustomerCreateDTO customerCreateDTO) {
        if (null == customerCreateDTO) {
            throw new MandatoryValueException(CLIENT_CREATE_DTO_ES_NULL);
        }

        return new Customer(null,
                customerCreateDTO.getIdentificationType(),
                customerCreateDTO.getIdentificationNumber(),
                customerCreateDTO.getName(),
                customerCreateDTO.getLastname(),
                customerCreateDTO.getEmail(),
                customerCreateDTO.getBirthday(),
                null,
                null
        );
    }

    public CustomerDTO modelToDto(Customer customer) {
        if (null == customer) {
            throw new MandatoryValueException(CLIENT_MODEL_IS_NULL);
        }

        return new CustomerDTO(
                customer.getId(),
                customer.getIdentificationType(),
                customer.getIdentificationNumber(),
                customer.getName(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getBirthday(),
                customer.getCreationDate(),
                customer.getModificationDate()
        );
    }

    public Customer updateDtoToModel(CustomerUpdateDTO customerUpdateDTO) {
        if (null == customerUpdateDTO) {
            throw new MandatoryValueException(CLIENT_UPDATE_DTO_IS_NULL);
        }
        return new Customer(customerUpdateDTO.getId(),
                customerUpdateDTO.getIdentificationType(),
                customerUpdateDTO.getIdentificationNumber(),
                customerUpdateDTO.getName(),
                customerUpdateDTO.getLastname(),
                customerUpdateDTO.getEmail(),
                customerUpdateDTO.getBirthday(),
                null,
                null
        );
    }

    public Customer entityToModel(CustomerEntity customerEntity) {
        if (null == customerEntity) {
            throw new MandatoryValueException(CLIENT_ENTITY_IS_NULL);
        }

        return new Customer(customerEntity.getId(),
                customerEntity.getIdentificationType(),
                customerEntity.getIdentificationNumber(),
                customerEntity.getName(),
                customerEntity.getLastname(),
                customerEntity.getEmail(),
                customerEntity.getBirthday(),
                customerEntity.getCreationDate(),
                customerEntity.getModificationDate()
        );
    }

    public CustomerEntity modelToEntity(Customer customer) {
        if (null == customer) {
            throw new MandatoryValueException(CLIENT_MODEL_IS_NULL);
        }
        return new CustomerEntity(customer.getId(),
                customer.getIdentificationType(),
                customer.getIdentificationNumber(),
                customer.getName(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getBirthday(),
                customer.getCreationDate(),
                customer.getModificationDate()
        );
    }
}
