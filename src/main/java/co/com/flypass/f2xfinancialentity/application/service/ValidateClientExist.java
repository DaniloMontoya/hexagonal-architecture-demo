package co.com.flypass.f2xfinancialentity.application.service;

import co.com.flypass.f2xfinancialentity.infrastructure.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.infrastructure.exception.NotFindException;
import co.com.flypass.f2xfinancialentity.domain.repository.ClientRepository;
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
public class ValidateClientExist {
    public static final String NOT_EXIST_CLIENT_MESSAGE = "No existe el cliente";
    public static final String REQUIRED_CLIENT_ID_MESSAGE = "Id de cliente requerido";
    private final ClientRepository clientRepository;
    public void existClientById(String clientId){
        if(null == clientId){
            log.error("Required client id");
            throw new MandatoryValueException(REQUIRED_CLIENT_ID_MESSAGE);
        }
        boolean exist = clientRepository.existClient(clientId);
        if(!exist){
            log.error("Not find client with id: {}", clientId);
            throw new NotFindException(NOT_EXIST_CLIENT_MESSAGE);
        }
    }
}
