package co.com.flypass.f2xfinancialentity.model.builder;

import co.com.flypass.f2xfinancialentity.enums.TransactionType;
import co.com.flypass.f2xfinancialentity.model.TransactionModel;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 1:18 PM
 **/
@Getter
public class TransactionBuilder {
    private String id;
    private TransactionType type;
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private LocalDateTime transactionDate;

    public TransactionModel build(){
        return new TransactionModel(id,
                type,
                sourceAccount,
                destinationAccount,
                amount,
                transactionDate);
    }

    public TransactionBuilder withType(TransactionType transactionType) {
        this.type = transactionType;
        return this;
    }

    public TransactionBuilder withDestinationAccount(String accountNumber) {
        this.destinationAccount = accountNumber;
        return this;
    }

    public TransactionBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder withTransactionDate(LocalDateTime date) {
        this.transactionDate = date;
        return this;
    }

    public TransactionBuilder withSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
        return this;
    }


}
