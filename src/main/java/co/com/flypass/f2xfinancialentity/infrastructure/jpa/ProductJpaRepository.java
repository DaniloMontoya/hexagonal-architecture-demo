package co.com.flypass.f2xfinancialentity.infrastructure.jpa;

import co.com.flypass.f2xfinancialentity.domain.entity.ProductAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 10:34 AM
 **/
@Repository
public interface ProductJpaRepository extends JpaRepository<ProductAccountEntity, String> {
    Optional<ProductAccountEntity> findByAccountNumber(String accountNumber);

    boolean existsByClientId(String id);
}
