package co.com.flypass.f2xfinancialentity.model.builder;

import co.com.flypass.f2xfinancialentity.enums.AccountStatus;
import co.com.flypass.f2xfinancialentity.enums.AccountType;
import co.com.flypass.f2xfinancialentity.model.ProductModel;
import co.com.flypass.f2xfinancialentity.model.dto.ProductCreateDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ProductDTO;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 9:38 AM
 **/
@Getter
public class ProductBuilder {
    private String id;
    private AccountType type;
    private String accountNumber;
    private AccountStatus status;
    private double balance;
    private boolean excludeGMF;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private String clientId;

    public ProductBuilder(ProductCreateDTO productCreateDTO) {
        this.type = productCreateDTO.getType();
        this.clientId = productCreateDTO.getClientId();
    }
    public ProductBuilder(ProductDTO productDTO) {
        this.id= productDTO.getId();
        this.type = productDTO.getType();
        this.clientId = productDTO.getClientId();
        this.accountNumber = productDTO.getAccountNumber();
        this.status = productDTO.getStatus();
        this.balance = productDTO.getBalance();
        this.excludeGMF = productDTO.isExcludeGMF();
        this.creationDate = productDTO.getCreationDate();
        this.modificationDate = productDTO.getModificationDate();
    }

    public ProductBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public ProductBuilder withStatus(AccountStatus status) {
        this.status = status;
        return this;
    }

    public ProductBuilder withBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public ProductBuilder withExcludeGMF(boolean excludeGMF) {
        this.excludeGMF = excludeGMF;
        return this;
    }

    public ProductBuilder withCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ProductBuilder withModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }


    public ProductModel build() {
        return new ProductModel(id,
                type,
                accountNumber,
                status,
                balance,
                excludeGMF,
                creationDate,
                modificationDate,
                clientId);
    }
}
