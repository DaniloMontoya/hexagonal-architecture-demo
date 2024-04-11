package co.com.flypass.f2xfinancialentity.domain.repository;

import co.com.flypass.f2xfinancialentity.application.model.ProductModel;

import java.util.List;
import java.util.Optional;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 8:53 AM
 **/
public interface ProductRepository {
    List<ProductModel> findAll();

    Optional<ProductModel> findById(String id);

    Optional<ProductModel> findByAccountNumber(String accountNumber);

    ProductModel create(ProductModel productModel);
    ProductModel update(ProductModel productModel);

    boolean existsById(String id);

    boolean existsByClientId(String clientId);
}
