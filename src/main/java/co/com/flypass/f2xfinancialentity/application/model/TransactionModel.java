package co.com.flypass.f2xfinancialentity.application.model;

import co.com.flypass.f2xfinancialentity.domain.enums.TransactionType;
import co.com.flypass.f2xfinancialentity.infrastructure.exception.NotAllowedOperationException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 1:27 PM
 **/
@Getter
public class TransactionModel {
    public static final String TRANSACTION_AMOUNT_MUST_MAYOR_ZERO_MESSAGE = "El valor de la transacción debe se mayor de $0 (cero)";

    private String id;
    private TransactionType type;
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private LocalDateTime transactionDate;

    public TransactionModel(String id, TransactionType type, String sourceAccount, String destinationAccount,
                            double amount, LocalDateTime transactionDate) {
        validateTransactionAmount(amount);
        this.id = generateId(id);
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public void validateTransactionAmount(double amount) {
        if (amount < 1) {
            throw new NotAllowedOperationException(TRANSACTION_AMOUNT_MUST_MAYOR_ZERO_MESSAGE);
        }
    }

    private String generateId(String id) {
        if(null == id){
            return UUID.randomUUID().toString();
        }
        return id;
    }

}
