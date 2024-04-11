package com.danilomontoya.hexagonalarchitecturedemo.application.service.impl;

import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountStatus;
import com.danilomontoya.hexagonalarchitecturedemo.domain.enums.AccountType;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.MandatoryValueException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.NotAllowedOperationException;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.NotFindException;
import com.danilomontoya.hexagonalarchitecturedemo.application.mapper.ProductMapper;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.builder.ProductBuilder;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.ProductCreateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.ProductDTO;
import com.danilomontoya.hexagonalarchitecturedemo.domain.repository.ProductRepository;
import com.danilomontoya.hexagonalarchitecturedemo.application.service.AccountNumberGenerator;
import com.danilomontoya.hexagonalarchitecturedemo.application.service.ProductService;
import com.danilomontoya.hexagonalarchitecturedemo.application.service.ValidateClientExist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 8:50 AM
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public static final String REQUIRED_PRODUCT_ID_MESSAGE = "Id de producto requerido";
    public static final String NOT_FIND_PRODUCT_MESSAGE = "No se encontro el producto";
    public static final String REQUIRED_ACCOUNT_NUMBER_MESSAGE = "Número de cuenta requerido";
    public static final String NOT_ALLOWED_PRODUCT_TYPE_MESSAGE = "No se puede crear el tipo de producto";
    public static final String REQUIRED_ACCOUNT_ID_MESSAGE = "Id de cuenta requerido";
    public static final String NOT_FOUND_ACCOUNT_MESSAGE = "No existe la cuenta";
    public static final String NOT_ZERO_BALANCE_ACCOUNT_MESSAGE = "El saldo de la cuenta debe ser 0";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ValidateClientExist validateClientExist;
    private final AccountNumberGenerator accountNumberGenerator;

    @Override
    public List<ProductDTO> findAll() {
        log.debug("Finding all products...");
        return productRepository.findAll()
                .stream()
                .map(productMapper::modelToDto)
                .toList();
    }

    @Override
    public ProductDTO findById(String id) {
        log.debug("Finding product by Id... {}", id);
        if(null == id){
            log.error("Error to find product, required id");
            throw new MandatoryValueException(REQUIRED_PRODUCT_ID_MESSAGE);
        }

        return productRepository.findById(id)
                .map(productMapper::modelToDto)
                .orElseThrow(()-> new NotFindException(NOT_FIND_PRODUCT_MESSAGE));

    }

    @Override
    public ProductDTO findByAccountNumber(String accountNumber) {
        log.debug("Finding product by Account Number... {}", accountNumber);
        if(null == accountNumber){
            log.error("Required account number");
            throw new MandatoryValueException(REQUIRED_ACCOUNT_NUMBER_MESSAGE);
        }

        return productRepository.findByAccountNumber(accountNumber)
                .map(productMapper::modelToDto)
                .orElseThrow(()-> new NotFindException(NOT_FIND_PRODUCT_MESSAGE));

    }

    @Override
    public ProductDTO create(ProductCreateDTO productCreateDTO) {
        log.debug("Creating product... {}", productCreateDTO);
        validateClientExist.existClientById(productCreateDTO.getClientId());
        var productBuilder = createBuilderProduct(productCreateDTO);
        var productModel = productRepository.create(productBuilder.build());
        log.info("Product created {}", productModel);
        return productMapper.modelToDto(productModel);
    }

    public ProductBuilder createBuilderProduct(ProductCreateDTO productCreateDTO){
        if(AccountType.SAVING.equals(productCreateDTO.getType())){
            log.debug("Creating product: Saving Account...");
            return createSavingAccount(productCreateDTO);
        }else if (AccountType.CHECKING.equals(productCreateDTO.getType())){
            log.debug("Creating product: Checking Account...");
            return createCheckingAccount(productCreateDTO);
        }else{
            log.error("Not allowed to create other Account Type");
            throw new NotAllowedOperationException(NOT_ALLOWED_PRODUCT_TYPE_MESSAGE);
        }
    }

    public ProductBuilder createSavingAccount(ProductCreateDTO productCreateDTO){
        var productBuilder = new ProductBuilder(productCreateDTO);
        String accountNumber = this.generateValidSavingAccountNumber();
        productBuilder
                .withAccountNumber(accountNumber)
                .withCreationDate(LocalDateTime.now())
                .withModificationDate(LocalDateTime.now())
                .withBalance(0d)
                .withStatus(AccountStatus.ACTIVE)
                .withExcludeGMF(true);
        return productBuilder;
    }

    public ProductBuilder createCheckingAccount(ProductCreateDTO productCreateDTO){
        var productBuilder = new ProductBuilder(productCreateDTO);
        String accountNumber = this.generateValidCheckingAccountNumber();
        productBuilder
                .withAccountNumber(accountNumber)
                .withCreationDate(LocalDateTime.now())
                .withModificationDate(LocalDateTime.now())
                .withBalance(0d)
                .withStatus(AccountStatus.INACTIVE)
                .withExcludeGMF(false);
        return productBuilder;
    }

    @Override
    public String enable(String id) {
        log.debug("Enable account... {}", id);
        validateAccountExist(id);
        var productDTO = findById(id);
        var productBuilder = new ProductBuilder(productDTO);
        productBuilder.withStatus(AccountStatus.ACTIVE);
        productBuilder.withModificationDate(LocalDateTime.now());
        var productModel = productRepository.create(productBuilder.build());
        log.info("Enabled account {}", productModel);
        return productMapper.modelToDto(productModel).getAccountNumber();
    }

    @Override
    public String disable(String id) {
        log.debug("Disable account... {}", id);
        validateAccountExist(id);
        var productDTO = findById(id);
        var productBuilder = new ProductBuilder(productDTO);
        productBuilder.withStatus(AccountStatus.INACTIVE);
        productBuilder.withModificationDate(LocalDateTime.now());
        var productModel = productRepository.create(productBuilder.build());
        log.info("Disabled account {}", productModel);
        return productMapper.modelToDto(productModel).getAccountNumber();
    }

    @Override
    public String cancel(String id) {
        log.debug("Canceling account... {}", id);
        validateAccountExist(id);
        var productDTO = findById(id);
        validateZeroProductBalance(productDTO.getBalance());
        var productBuilder = new ProductBuilder(productDTO);
        productBuilder.withStatus(AccountStatus.CANCELLED);
        productBuilder.withModificationDate(LocalDateTime.now());
        var productModel = productRepository.create(productBuilder.build());
        log.debug("Cancelled account... {}", productModel);
        return productMapper.modelToDto(productModel).getAccountNumber();
    }

    @Override
    public void validateZeroProductBalance(double balance) {
        if(!isZeroBalance(balance)){
            log.error("Account must balance to Zero for Cancel");
            throw new NotAllowedOperationException(NOT_ZERO_BALANCE_ACCOUNT_MESSAGE);
        }
    }

    @Override
    public void validateAccountExist(String id) {
        if(null == id){
            log.error("Required account with id: {}", id);
            throw new MandatoryValueException(REQUIRED_ACCOUNT_ID_MESSAGE);
        }

        if(!productRepository.existsById(id)){
            log.error("Not find account with id: {}", id);
            throw new NotFindException(NOT_FOUND_ACCOUNT_MESSAGE);
        }
    }

    @Override
    public boolean isZeroBalance(double balance) {
        return balance == 0;
    }


    public String generateValidSavingAccountNumber() {
        log.debug("Generating Saving Account number...");
        String accountNumber = accountNumberGenerator.generateSavingNumber();
        boolean exist = existAccountNumber(accountNumber);
        while(exist){
            accountNumber = accountNumberGenerator.generateSavingNumber();
            exist = existAccountNumber(accountNumber);
        }
        log.debug("Creating Saving Account... {}", accountNumber);
        return accountNumber;
    }

    public String generateValidCheckingAccountNumber() {
        log.debug("Generating Checking Account number...");
        String accountNumber = accountNumberGenerator.generateCheckingNumber();
        boolean exist = existAccountNumber(accountNumber);
        while(exist){
            accountNumber = accountNumberGenerator.generateCheckingNumber();
            exist = this.existAccountNumber(accountNumber);
        }
        log.debug("Creating Checking Account... {}", accountNumber);
        return accountNumber;
    }

    @Override
    public boolean existAccountNumber(String accountNumber) {
        return productRepository.findByAccountNumber(accountNumber)
                .isPresent();
    }
}
