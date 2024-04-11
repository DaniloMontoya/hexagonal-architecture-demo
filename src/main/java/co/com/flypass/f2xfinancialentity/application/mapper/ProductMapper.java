package co.com.flypass.f2xfinancialentity.application.mapper;

import co.com.flypass.f2xfinancialentity.domain.entity.ClientEntity;
import co.com.flypass.f2xfinancialentity.domain.entity.ProductAccountEntity;
import co.com.flypass.f2xfinancialentity.infrastructure.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.application.model.ProductModel;
import co.com.flypass.f2xfinancialentity.application.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 8:54 AM
 **/
@Component
public class ProductMapper {
    public ProductDTO modelToDto(ProductModel productModel) {
        if(null == productModel){
            throw new MandatoryValueException("Modelo requerido");
        }
        var productDTO = new ProductDTO();
        productDTO.setId(productModel.getId());
        productDTO.setType(productModel.getType());
        productDTO.setAccountNumber(productModel.getAccountNumber());
        productDTO.setStatus(productModel.getStatus());
        productDTO.setBalance(productModel.getBalance());
        productDTO.setExcludeGMF(productModel.isExcludeGMF());
        productDTO.setCreationDate(productModel.getCreationDate());
        productDTO.setModificationDate(productModel.getModificationDate());
        productDTO.setClientId(productModel.getClientId());
        return productDTO;
    }

    public ProductModel entityToModel(ProductAccountEntity product) {
        if(null == product){
            throw new MandatoryValueException("Entidad requerida");
        }
        return new ProductModel(
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

    public ProductAccountEntity modelToEntity(ProductModel productModel) {
        if(null == productModel){
            throw new MandatoryValueException("Modelo requerido");
        }
        return new ProductAccountEntity(
                productModel.getId(),
                productModel.getType(),
                productModel.getAccountNumber(),
                productModel.getStatus(),
                productModel.getBalance(),
                productModel.isExcludeGMF(),
                productModel.getCreationDate(),
                productModel.getModificationDate(),
                new ClientEntity(productModel.getClientId())
        );
    }
}
