package co.com.flypass.f2xfinancialentity.service.impl;

import co.com.flypass.f2xfinancialentity.exception.DuplicityValueException;
import co.com.flypass.f2xfinancialentity.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.exception.NotAllowedOperationException;
import co.com.flypass.f2xfinancialentity.exception.NotFindException;
import co.com.flypass.f2xfinancialentity.mapper.ClientBuilder;
import co.com.flypass.f2xfinancialentity.model.ClientModel;
import co.com.flypass.f2xfinancialentity.model.dto.ClientCreateDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ClientDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ClientUpdateDTO;
import co.com.flypass.f2xfinancialentity.repository.ClientRepository;
import co.com.flypass.f2xfinancialentity.service.ClientService;
import co.com.flypass.f2xfinancialentity.service.ValidateProductsService;
import lombok.RequiredArgsConstructor;
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
public class ClientServiceImpl implements ClientService {
    public static final String REQUIRED_CLIENT_ID_MESSAGE = "Id del cliente requerido";
    public static final String CLIENT_HAVE_PRODUCTS = "El cliente tiene productos con la entidad";
    public static final String NOT_FIND_CLIENT_MESSAGE = "No existe cliente con el id:";
    private final ClientRepository clientRepository;
    private final ClientBuilder clientBuilder;
    private final ValidateProductsService validateProductsService;

    @Override
    public ClientDTO create(ClientCreateDTO clientCreateDTO) {
        ClientModel clientModel = clientBuilder.createDtoToModel(clientCreateDTO);
        boolean exist = clientRepository.existClientByDocumentNumber(clientModel.getIdentificationNumber());
        if(exist){
            throw new DuplicityValueException("Ya existe un cliente con el número de documento");
        }
        clientModel.setCreationDate(LocalDateTime.now());
        clientModel.setModificationDate(LocalDateTime.now());
        return clientBuilder.modelToDto(clientRepository.createClient(clientModel));
    }

    @Override
    public ClientDTO update(ClientUpdateDTO clientUpdateDTO) {
        var savedClient = clientRepository.getClient(clientUpdateDTO.getId())
                .orElseThrow(()-> new NotFindException(NOT_FIND_CLIENT_MESSAGE + clientUpdateDTO.getId()));

        ClientModel clientModel = clientBuilder.updateDtoToModel(clientUpdateDTO);
        clientModel.setModificationDate(LocalDateTime.now());
        clientModel.setCreationDate(savedClient.getCreationDate());
        return clientBuilder.modelToDto(clientRepository.updateClient(clientModel));
    }

    @Override
    public List<ClientDTO> findAll() {
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
        findClientById(id);
        boolean isHaveProducts = validateProductsService.existProductsByClientId(id);
        if(isHaveProducts){
            throw new NotAllowedOperationException(CLIENT_HAVE_PRODUCTS);
        }
        clientRepository.deleteClient(id);
    }
}
