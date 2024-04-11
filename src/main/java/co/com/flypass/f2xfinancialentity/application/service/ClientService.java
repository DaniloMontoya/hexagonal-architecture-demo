package co.com.flypass.f2xfinancialentity.application.service;

import co.com.flypass.f2xfinancialentity.application.model.dto.ClientCreateDTO;
import co.com.flypass.f2xfinancialentity.application.model.dto.ClientDTO;
import co.com.flypass.f2xfinancialentity.application.model.dto.ClientUpdateDTO;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 6:53 PM
 **/
public interface ClientService {
    ClientDTO create(ClientCreateDTO clientCreateDTO);
    ClientDTO update(ClientUpdateDTO clientCreateDTO);
    List<ClientDTO> findAll();
    ClientDTO findClientById(String id);
    void delete(String id);

}
