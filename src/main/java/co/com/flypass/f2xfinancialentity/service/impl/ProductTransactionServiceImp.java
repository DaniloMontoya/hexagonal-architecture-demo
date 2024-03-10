package co.com.flypass.f2xfinancialentity.service.impl;

import co.com.flypass.f2xfinancialentity.exception.NotAllowedOperationException;
import co.com.flypass.f2xfinancialentity.mapper.ProductMapper;
import co.com.flypass.f2xfinancialentity.model.builder.ProductBuilder;
import co.com.flypass.f2xfinancialentity.model.dto.ProductDTO;
import co.com.flypass.f2xfinancialentity.repository.ProductRepository;
import co.com.flypass.f2xfinancialentity.service.ProductTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 2:07 PM
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductTransactionServiceImp implements ProductTransactionService {

    public static final String INSUFFICIENT_FUNDS_MESSAGE = "Fondos insuficientes";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Transactional
    @Override
    public ProductDTO consignment(ProductDTO destinationProduct, double amount) {
        log.debug("Generating account financial movement... {}", destinationProduct.getAccountNumber());
        var productBuilder = new ProductBuilder(destinationProduct);
        productBuilder.withModificationDate(LocalDateTime.now());
        productBuilder.withBalance(destinationProduct.getBalance() + amount);
        var productModel = productRepository.update(productBuilder.build());
        log.info("Success consignment: {}, amount: {}",destinationProduct.getAccountNumber(), amount);
        return productMapper.modelToDto(productModel);
    }

    @Transactional
    @Override
    public ProductDTO withdrawal(ProductDTO sourceProduct, double amount) {
        log.debug("Generating account financial movement... {}", sourceProduct.getAccountNumber());
        validateAvailableBalance(sourceProduct.getBalance(), amount);
        var productBuilder = new ProductBuilder(sourceProduct);
        productBuilder.withModificationDate(LocalDateTime.now());
        productBuilder.withBalance(sourceProduct.getBalance() - amount);
        var productModel = productRepository.update(productBuilder.build());
        log.info("Success withdrawal: {}, amount: {}",sourceProduct.getAccountNumber(), amount);
        return productMapper.modelToDto(productModel);
    }

    public void validateAvailableBalance(double availableBalance, double amount){
        if(availableBalance < amount){
            log.error("insufficient founds");
            throw new NotAllowedOperationException(INSUFFICIENT_FUNDS_MESSAGE);
        }
    }
}
