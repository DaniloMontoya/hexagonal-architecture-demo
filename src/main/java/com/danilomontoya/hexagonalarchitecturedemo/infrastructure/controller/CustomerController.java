package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.controller;

import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerCreateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.customer.CustomerUpdateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 7:10 PM
 **/
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "Listar Clientes", description = "Obtiene la lista de todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Listado con la lista de clientes ó una lista vacia en caso de no existir clientes")
    @GetMapping("all")
    public List<CustomerDTO> findAll(){
        return customerService.findAll();
    }

    @Operation(summary = "Obtener Cliente por ID", description = "Busca un cliente especifico por el ID")
    @Parameter(description = "Id del Cliente que se quiere buscar", required = true, name = "id", in = ParameterIn.PATH)
    @ApiResponse(responseCode = "200", description = "Cliente encontrado con el id especificado", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "404", description = "Cuando no se encuentra un cliente por el id especificado")
    @GetMapping("clientById/{id}")
    public CustomerDTO findById(@PathVariable(name = "id") String id){
        return customerService.findClientById(id);
    }

    @Operation(summary = "Crear Cliente", description = "Crea un nuevo cliente en el sistema")
    @ApiResponse(responseCode = "200", description = "Cuando se crea el Cliente correctamente", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "Cuando no se envia un dato que es requerido")
    @ApiResponse(responseCode = "404", description = "Cuando no encuentra el cliente para actualizar")
    @ApiResponse(responseCode = "409", description = "Cuando ya existe un Cliente con el número de identificación")
    @ApiResponse(responseCode = "422", description = "Cuando hay alguna restricción no valida para crear el cliente")
    @ApiResponse(responseCode = "500", description = "Cuando ocurre una excepcion inesperada al crear el Cliente")
    @PostMapping("create")
    public CustomerDTO create(@RequestBody CustomerCreateDTO customerCreateDTO){
        return customerService.create(customerCreateDTO);
    }

    @Operation(summary = "Actualizar Cliente", description = "Actualiza el cliente con los datos indocados")
    @ApiResponse(responseCode = "200", description = "Cuando se actualiza el Cliente correctamente", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "Cuando no se envia un dato que es requerido")
    @ApiResponse(responseCode = "422", description = "Cuando hay alguna restricción no valida para actualizar el cliente")
    @ApiResponse(responseCode = "500", description = "Cuando ocurre una excepcion inesperada actualizando el Cliente")
    @PostMapping("update")
    public CustomerDTO update(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        return customerService.update(customerUpdateDTO);
    }

    @Operation(summary = "Eliminar Cliente", description = "Elimina el cliente por ID")
    @ApiResponse(responseCode = "200", description = "Cuando se elimina el Cliente correctamente")
    @ApiResponse(responseCode = "404", description = "Cuando no encuentra el cliente")
    @ApiResponse(responseCode = "405", description = "Cuando el cliente tiene productos asociados")
    @ApiResponse(responseCode = "500", description = "Cuando ocurre una excepcion inesperada")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") String id){
        customerService.delete(id);
    }
}
