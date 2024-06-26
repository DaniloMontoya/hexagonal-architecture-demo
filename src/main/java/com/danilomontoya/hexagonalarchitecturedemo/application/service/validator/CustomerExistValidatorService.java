package com.danilomontoya.hexagonalarchitecturedemo.application.service.validator;

import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.MandatoryValueException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.NotFindException;
import com.danilomontoya.hexagonalarchitecturedemo.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 9:24 AM
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerExistValidatorService {
    public static final String NOT_EXIST_CLIENT_MESSAGE = "No existe el cliente";
    public static final String REQUIRED_CLIENT_ID_MESSAGE = "Id de cliente requerido";
    private final CustomerRepository customerRepository;
    public void existClientById(String clientId){
        if(null == clientId){
            log.error("Required client id");
            throw new MandatoryValueException(REQUIRED_CLIENT_ID_MESSAGE);
        }
        boolean exist = customerRepository.existClient(clientId);
        if(!exist){
            log.error("Not find client with id: {}", clientId);
            throw new NotFindException(NOT_EXIST_CLIENT_MESSAGE);
        }
    }
}
