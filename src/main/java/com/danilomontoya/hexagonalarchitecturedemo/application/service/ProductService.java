package com.danilomontoya.hexagonalarchitecturedemo.application.service;

import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.product.ProductCreateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.product.ProductDTO;

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
