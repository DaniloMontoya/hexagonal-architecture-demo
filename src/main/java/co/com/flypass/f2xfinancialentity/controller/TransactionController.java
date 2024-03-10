package co.com.flypass.f2xfinancialentity.controller;

import co.com.flypass.f2xfinancialentity.model.dto.transaction.ConsignmentDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.TransactionDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.TransferAccountDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.WithdrawalDTO;
import co.com.flypass.f2xfinancialentity.service.TransactionService;
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

    @GetMapping("all")
    public List<TransactionDTO> getAll(){
        return transactionService.findAll();
    }

    @PostMapping("consignment")
    public TransactionDTO consignment(@RequestBody ConsignmentDTO consignmentDTO){
        return transactionService.consignment(consignmentDTO);
    }
    @PostMapping("withdrawal")
    public TransactionDTO withdrawal(@RequestBody WithdrawalDTO withdrawalDTO){
        return transactionService.withdrawal(withdrawalDTO);
    }

    @PostMapping("transferAccounts")
    public TransactionDTO transferBetweenAccounts(@RequestBody TransferAccountDTO transferAccountDTO){
        return transactionService.transferBetweenAccounts(transferAccountDTO);
    }
}
