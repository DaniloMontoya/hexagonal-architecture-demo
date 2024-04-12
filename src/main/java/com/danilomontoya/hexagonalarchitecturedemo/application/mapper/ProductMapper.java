package com.danilomontoya.hexagonalarchitecturedemo.application.mapper;

import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.entity.CustomerEntity;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.repository.entity.ProductAccountEntity;
import com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception.MandatoryValueException;
import com.danilomontoya.hexagonalarchitecturedemo.domain.entity.Product;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.product.ProductDTO;
import org.springframework.stereotype.Component;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 8:54 AM
 **/
@Component
public class ProductMapper {
    public ProductDTO modelToDto(Product product) {
        if(null == product){
            throw new MandatoryValueException("Modelo requerido");
        }
        var productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setType(product.getType());
        productDTO.setAccountNumber(product.getAccountNumber());
        productDTO.setStatus(product.getStatus());
        productDTO.setBalance(product.getBalance());
        productDTO.setExcludeGMF(product.isExcludeGMF());
        productDTO.setCreationDate(product.getCreationDate());
        productDTO.setModificationDate(product.getModificationDate());
        productDTO.setClientId(product.getClientId());
        return productDTO;
    }

    public Product entityToModel(ProductAccountEntity product) {
        if(null == product){
            throw new MandatoryValueException("Entidad requerida");
        }
        return new Product(
                product.getId(),
                product.getType(),
                product.getAccountNumber(),
                product.getStatus(),
                product.getBalance(),
                product.isExcludeGMF(),
                product.getCreationDate(),
                product.getModificationDate(),
                product.getClient().getId()
        );
    }

    public ProductAccountEntity modelToEntity(Product product) {
        if(null == product){
            throw new MandatoryValueException("Modelo requerido");
        }
        return new ProductAccountEntity(
                product.getId(),
                product.getType(),
                product.getAccountNumber(),
                product.getStatus(),
                product.getBalance(),
                product.isExcludeGMF(),
                product.getCreationDate(),
                product.getModificationDate(),
                new CustomerEntity(product.getClientId())
        );
    }
}
