package co.com.flypass.f2xfinancialentity.service;

import co.com.flypass.f2xfinancialentity.model.dto.ClientCreateDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ClientDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ClientUpdateDTO;

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
    ClientDTO findOne(String id);
    void delete(String id);

}
