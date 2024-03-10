package co.com.flypass.f2xfinancialentity.service.impl;

import co.com.flypass.f2xfinancialentity.enums.AccountStatus;
import co.com.flypass.f2xfinancialentity.enums.AccountType;
import co.com.flypass.f2xfinancialentity.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.exception.NotAllowedOperationException;
import co.com.flypass.f2xfinancialentity.exception.NotFindException;
import co.com.flypass.f2xfinancialentity.mapper.ProductMapper;
import co.com.flypass.f2xfinancialentity.model.builder.ProductBuilder;
import co.com.flypass.f2xfinancialentity.model.dto.ProductCreateDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ProductDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ProductUpdateDTO;
import co.com.flypass.f2xfinancialentity.repository.ProductRepository;
import co.com.flypass.f2xfinancialentity.service.AccountNumberGenerator;
import co.com.flypass.f2xfinancialentity.service.ProductService;
import co.com.flypass.f2xfinancialentity.service.ValidateClientExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 8:50 AM
 **/
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
        return productRepository.findAll()
                .stream()
                .map(productMapper::modelToDto)
                .toList();
    }

    @Override
    public ProductDTO findById(String id) {
        if(null == id){
            throw new MandatoryValueException(REQUIRED_PRODUCT_ID_MESSAGE);
        }

        return productRepository.findById(id)
                .map(productMapper::modelToDto)
                .orElseThrow(()-> new NotFindException(NOT_FIND_PRODUCT_MESSAGE));

    }

    @Override
    public ProductDTO findByAccountNumber(String accountNumber) {
        if(null == accountNumber){
            throw new MandatoryValueException(REQUIRED_ACCOUNT_NUMBER_MESSAGE);
        }

        return productRepository.findByAccountNumber(accountNumber)
                .map(productMapper::modelToDto)
                .orElseThrow(()-> new NotFindException(NOT_FIND_PRODUCT_MESSAGE));

    }

    @Override
    public ProductDTO create(ProductCreateDTO productCreateDTO) {
        validateClientExist.existClientById(productCreateDTO.getClientId());
        var productBuilder = createBuilderProduct(productCreateDTO);
        var productModel = productRepository.create(productBuilder.build());
        return productMapper.modelToDto(productModel);
    }

    public ProductBuilder createBuilderProduct(ProductCreateDTO productCreateDTO){
        if(AccountType.SAVING.equals(productCreateDTO.getType())){
            return createSavingAccount(productCreateDTO);
        }else if (AccountType.CHECKING.equals(productCreateDTO.getType())){
            return createCheckingAccount(productCreateDTO);
        }else{
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
    public ProductDTO update(ProductUpdateDTO productUpdateDTO) {
        return null;
    }

    @Override
    public String enable(String id) {
        validateAccountExist(id);
        var productDTO = findById(id);
        var productBuilder = new ProductBuilder(productDTO);
        productBuilder.withStatus(AccountStatus.ACTIVE);
        productBuilder.withModificationDate(LocalDateTime.now());
        var productModel = productRepository.create(productBuilder.build());
        return productMapper.modelToDto(productModel).getAccountNumber();
    }

    @Override
    public String disable(String id) {
        validateAccountExist(id);
        var productDTO = findById(id);
        var productBuilder = new ProductBuilder(productDTO);
        productBuilder.withStatus(AccountStatus.INACTIVE);
        productBuilder.withModificationDate(LocalDateTime.now());
        var productModel = productRepository.create(productBuilder.build());
        return productMapper.modelToDto(productModel).getAccountNumber();
    }

    @Override
    public String cancel(String id) {
        validateAccountExist(id);
        var productDTO = findById(id);
        validateZeroProductBalance(productDTO.getBalance());
        var productBuilder = new ProductBuilder(productDTO);
        productBuilder.withStatus(AccountStatus.CANCELLED);
        productBuilder.withModificationDate(LocalDateTime.now());
        var productModel = productRepository.create(productBuilder.build());
        return productMapper.modelToDto(productModel).getAccountNumber();
    }

    @Override
    public void validateZeroProductBalance(double balance) {
        if(!isZeroBalance(balance)){
            throw new NotAllowedOperationException(NOT_ZERO_BALANCE_ACCOUNT_MESSAGE);
        }
    }

    @Override
    public void validateAccountExist(String id) {
        if(null == id){
            throw new MandatoryValueException(REQUIRED_ACCOUNT_ID_MESSAGE);
        }

        if(!productRepository.existsById(id)){
            throw new NotFindException(NOT_FOUND_ACCOUNT_MESSAGE);
        }
    }

    @Override
    public boolean isZeroBalance(double balance) {
        return balance == 0;
    }


    public String generateValidSavingAccountNumber() {
        String accountNumber = accountNumberGenerator.generateSavingNumber();
        boolean exist = existAccountNumber(accountNumber);
        while(exist){
            accountNumber = accountNumberGenerator.generateSavingNumber();
            exist = existAccountNumber(accountNumber);
        }
        return accountNumber;
    }

    public String generateValidCheckingAccountNumber() {
        String accountNumber = accountNumberGenerator.generateCheckingNumber();
        boolean exist = existAccountNumber(accountNumber);
        while(exist){
            accountNumber = accountNumberGenerator.generateCheckingNumber();
            exist = this.existAccountNumber(accountNumber);
        }
        return accountNumber;
    }

    @Override
    public boolean existAccountNumber(String accountNumber) {
        return productRepository.findByAccountNumber(accountNumber)
                .isPresent();
    }
}
