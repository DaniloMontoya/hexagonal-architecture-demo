package co.com.flypass.f2xfinancialentity.mapper;

import co.com.flypass.f2xfinancialentity.entity.TransactionEntity;
import co.com.flypass.f2xfinancialentity.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.model.TransactionModel;
import co.com.flypass.f2xfinancialentity.model.dto.transaction.TransactionDTO;
import org.springframework.stereotype.Service;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 1:31 PM
 **/
@Service
public class TransactionMapper {
    public TransactionDTO modelToDTO(TransactionModel transactionModel) {
        validateNullObject(transactionModel);
        return new TransactionDTO(
                transactionModel.getId(),
                transactionModel.getType(),
                transactionModel.getSourceAccount(),
                transactionModel.getDestinationAccount(),
                transactionModel.getAmount(),
                transactionModel.getTransactionDate()
        );
    }

    public TransactionEntity modelToEntity(TransactionModel transactionModel) {
        validateNullObject(transactionModel);
        return new TransactionEntity(
                transactionModel.getId(),
                transactionModel.getType(),
                transactionModel.getSourceAccount(),
                transactionModel.getDestinationAccount(),
                transactionModel.getAmount(),
                transactionModel.getTransactionDate()
        );
    }

    public TransactionModel entityToModel(TransactionEntity transactionEntity) {
        validateNullObject(transactionEntity);
        return new TransactionModel(
                transactionEntity.getId(),
                transactionEntity.getType(),
                transactionEntity.getSourceAccount(),
                transactionEntity.getDestinationAccount(),
                transactionEntity.getAmount(),
                transactionEntity.getTransactionDate()
        );
    }

    private void validateNullObject(Object obj) {
        if (null == obj) {
            throw new MandatoryValueException("Entidad Transacción requerida");
        }
    }
}
