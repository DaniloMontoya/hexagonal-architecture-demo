package co.com.flypass.f2xfinancialentity.repository;

import co.com.flypass.f2xfinancialentity.model.TransactionModel;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 1:31 PM
 **/
public interface TransactionRepository {
    TransactionModel save(TransactionModel transactionModel);
}
