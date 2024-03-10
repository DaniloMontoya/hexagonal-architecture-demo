package co.com.flypass.f2xfinancialentity.service;

import co.com.flypass.f2xfinancialentity.model.dto.ProductCreateDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ProductDTO;

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

    String enable(String id);

    String disable(String id);

    String cancel(String id);

    void validateZeroProductBalance(double balance);

    void validateAccountExist(String id);

    boolean isZeroBalance(double balance);

    boolean existAccountNumber(String accountNumber);
}
