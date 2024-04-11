package co.com.flypass.f2xfinancialentity.application.mapper;

import co.com.flypass.f2xfinancialentity.domain.entity.ClientEntity;
import co.com.flypass.f2xfinancialentity.infrastructure.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.application.model.ClientModel;
import co.com.flypass.f2xfinancialentity.application.model.dto.ClientCreateDTO;
import co.com.flypass.f2xfinancialentity.application.model.dto.ClientDTO;
import co.com.flypass.f2xfinancialentity.application.model.dto.ClientUpdateDTO;
import org.springframework.stereotype.Component;


/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 7:37 PM
 **/
@Component
public class ClientBuilder {

    public static final String CLIENT_CREATE_DTO_ES_NULL = "ClientCreateDTO es nula";
    public static final String CLIENT_UPDATE_DTO_IS_NULL = "ClientUpdateDTO es nula";
    public static final String CLIENT_MODEL_IS_NULL = "ClientModel es nula";
    public static final String CLIENT_ENTITY_IS_NULL = "ClientEntity es nula";

    public ClientModel createDtoToModel(ClientCreateDTO clientCreateDTO) {
        if (null == clientCreateDTO) {
            throw new MandatoryValueException(CLIENT_CREATE_DTO_ES_NULL);
        }

        return new ClientModel(null,
                clientCreateDTO.getIdentificationType(),
                clientCreateDTO.getIdentificationNumber(),
                clientCreateDTO.getName(),
                clientCreateDTO.getLastname(),
                clientCreateDTO.getEmail(),
                clientCreateDTO.getBirthday(),
                null,
                null
        );
    }

    public ClientDTO modelToDto(ClientModel clientModel) {
        if (null == clientModel) {
            throw new MandatoryValueException(CLIENT_MODEL_IS_NULL);
        }

        return new ClientDTO(
                clientModel.getId(),
                clientModel.getIdentificationType(),
                clientModel.getIdentificationNumber(),
                clientModel.getName(),
                clientModel.getLastname(),
                clientModel.getEmail(),
                clientModel.getBirthday(),
                clientModel.getCreationDate(),
                clientModel.getModificationDate()
        );
    }

    public ClientModel updateDtoToModel(ClientUpdateDTO clientUpdateDTO) {
        if (null == clientUpdateDTO) {
            throw new MandatoryValueException(CLIENT_UPDATE_DTO_IS_NULL);
        }
        return new ClientModel(clientUpdateDTO.getId(),
                clientUpdateDTO.getIdentificationType(),
                clientUpdateDTO.getIdentificationNumber(),
                clientUpdateDTO.getName(),
                clientUpdateDTO.getLastname(),
                clientUpdateDTO.getEmail(),
                clientUpdateDTO.getBirthday(),
                null,
                null
        );
    }

    public ClientModel entityToModel(ClientEntity clientEntity) {
        if (null == clientEntity) {
            throw new MandatoryValueException(CLIENT_ENTITY_IS_NULL);
        }

        return new ClientModel(clientEntity.getId(),
                clientEntity.getIdentificationType(),
                clientEntity.getIdentificationNumber(),
                clientEntity.getName(),
                clientEntity.getLastname(),
                clientEntity.getEmail(),
                clientEntity.getBirthday(),
                clientEntity.getCreationDate(),
                clientEntity.getModificationDate()
        );
    }

    public ClientEntity modelToEntity(ClientModel clientModel) {
        if (null == clientModel) {
            throw new MandatoryValueException(CLIENT_MODEL_IS_NULL);
        }
        return new ClientEntity(clientModel.getId(),
                clientModel.getIdentificationType(),
                clientModel.getIdentificationNumber(),
                clientModel.getName(),
                clientModel.getLastname(),
                clientModel.getEmail(),
                clientModel.getBirthday(),
                clientModel.getCreationDate(),
                clientModel.getModificationDate()
        );
    }
}
