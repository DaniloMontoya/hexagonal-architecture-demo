package com.danilomontoya.hexagonalarchitecturedemo.application.service;

import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerCreateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerUpdateDTO;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 6:53 PM
 **/
public interface CustomerService {
    CustomerDTO create(CustomerCreateDTO customerCreateDTO);
    CustomerDTO update(CustomerUpdateDTO clientCreateDTO);
    List<CustomerDTO> findAll();
    CustomerDTO findClientById(String id);
    void delete(String id);

}
