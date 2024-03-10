package co.com.flypass.f2xfinancialentity.controller;

import co.com.flypass.f2xfinancialentity.model.dto.transaction.ConsignmentDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.TransactionDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.TransferAccountDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.WithdrawalDTO;
import co.com.flypass.f2xfinancialentity.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:09 PM
 **/
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Lista de Transacciones", description = "Obtiene la lista de todas las transacciones")
    @ApiResponse(responseCode = "200", description = "Cuando se ejecuta la consulta correctamente")
    @GetMapping("all")
    public List<TransactionDTO> getAll(){
        return transactionService.findAll();
    }

    @Operation(summary = "Consignacion a cuenta", description = "Realiza consignación (abono) a una cuenta")
    @ApiResponse(responseCode = "200", description = "Cuando se realiza la consignación (abono) correctamente")
    @ApiResponse(responseCode = "404", description = "Cuando no se encuentra el producto para realizar la transacción", content = {@Content(mediaType = "JSON")})
    @ApiResponse(responseCode = "405", description = "Cuando el monto de la transaccion es $0 (cero)", content = {@Content(mediaType = "JSON")})
    @PostMapping("consignment")
    public TransactionDTO consignment(@RequestBody ConsignmentDTO consignmentDTO){
        return transactionService.consignment(consignmentDTO);
    }
    @Operation(summary = "Retiro desde cuenta", description = "Realiza un retiro desde una cuenta de la entidad financiera")
    @ApiResponse(responseCode = "200", description = "Cuando se realiza el retiro correctamente")
    @ApiResponse(responseCode = "404", description = "Cuando no se encuentra el producto para realizar la transacción", content = {@Content(mediaType = "JSON")})
    @ApiResponse(responseCode = "405", description = "Cuando el monto de la transaccion es $0 (cero)", content = {@Content(mediaType = "JSON")})
    @PostMapping("withdrawal")
    public TransactionDTO withdrawal(@RequestBody WithdrawalDTO withdrawalDTO){
        return transactionService.withdrawal(withdrawalDTO);
    }

    @Operation(summary = "Transferencia entre cuentas", description = "Realiza transferencias entre cuentas de la entidad financiera")
    @ApiResponse(responseCode = "200", description = "Cuando se realiza la transferencia correctamente")
    @ApiResponse(responseCode = "404", description = "Cuando no se encuentra algunos de los productos para realizar la transacción", content = {@Content(mediaType = "JSON")})
    @ApiResponse(responseCode = "405", description = "Cuando el monto de la transaccion es $0 (cero)", content = {@Content(mediaType = "JSON")})
    @ApiResponse(responseCode = "405", description = "Cuando la cuenta de origen no posee fondos para realizar la transacion", content = {@Content(mediaType = "JSON")})
    @PostMapping("transferBetweenAccounts")
    public TransactionDTO transferBetweenAccounts(@RequestBody TransferAccountDTO transferAccountDTO){
        return transactionService.transferBetweenAccounts(transferAccountDTO);
    }
}
