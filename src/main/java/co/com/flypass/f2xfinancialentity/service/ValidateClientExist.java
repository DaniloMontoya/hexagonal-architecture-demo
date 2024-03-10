package co.com.flypass.f2xfinancialentity.service;

import co.com.flypass.f2xfinancialentity.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.exception.NotFindException;
import co.com.flypass.f2xfinancialentity.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 9:24 AM
 **/
@Service
@RequiredArgsConstructor
public class ValidateClientExist {
    public static final String NOT_EXIST_CLIENT_MESSAGE = "No existe el cliente";
    public static final String REQUIRED_CLIENT_ID_MESSAGE = "Id de cliente requerido";
    private final ClientRepository clientRepository;
    public void existClientById(String clientId){
        if(null == clientId){
            throw new MandatoryValueException(REQUIRED_CLIENT_ID_MESSAGE);
        }
        boolean exist = clientRepository.existClient(clientId);
        if(!exist){
            throw new NotFindException(NOT_EXIST_CLIENT_MESSAGE);
        }
    }
}
