package co.com.flypass.f2xfinancialentity.controller;

import co.com.flypass.f2xfinancialentity.model.dto.ClientCreateDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ClientDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ClientUpdateDTO;
import co.com.flypass.f2xfinancialentity.service.ClientService;
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
public class ClientController {
    private final ClientService clientService;

    @GetMapping("all")
    public List<ClientDTO> findAll(){
        return clientService.findAll();
    }

    @GetMapping("clientById/{id}")
    public ClientDTO findById(@PathVariable(name = "id") String id){
        return clientService.findOne(id);
    }

    @PostMapping("create")
    public ClientDTO create(@RequestBody ClientCreateDTO clientCreateDTO){
        return clientService.create(clientCreateDTO);
    }

    @PostMapping("update")
    public ClientDTO update(@RequestBody ClientUpdateDTO clientUpdateDTO){
        return clientService.update(clientUpdateDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") String id){
        clientService.delete(id);
    }
}
