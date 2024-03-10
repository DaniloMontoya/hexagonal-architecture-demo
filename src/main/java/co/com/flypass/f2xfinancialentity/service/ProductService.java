package co.com.flypass.f2xfinancialentity.service;

import co.com.flypass.f2xfinancialentity.exception.InvalidValueException;
import co.com.flypass.f2xfinancialentity.model.dto.ProductCreateDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ProductDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ProductUpdateDTO;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:07 AM
 **/
public interface ProductService {
    List<ProductDTO> findAll();

    ProductDTO findById(String id);

    ProductDTO findByAccountNumber(String accountNumber);

    ProductDTO create(ProductCreateDTO productCreateDTO);

    ProductDTO update(ProductUpdateDTO productUpdateDTO);

    String enable(String id);

    String disable(String id);

    boolean isZeroBalance(double balance);

    void validateAccountNumber() throws InvalidValueException;
}
