package co.com.flypass.f2xfinancialentity.service;

import co.com.flypass.f2xfinancialentity.model.dto.transaction.ConsignmentDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.TransactionDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.TransferAccountDTO;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.WithdrawalDTO;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:31 PM
 **/
public interface TransactionService {
    TransactionDTO consignment(ConsignmentDTO consignmentDTO);

    TransactionDTO withdrawal(WithdrawalDTO withdrawalDTO);

    TransactionDTO transferBetweenAccounts(TransferAccountDTO transferAccountDTO);
}
