package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created By artemvlasov on 29/12/2017
 **/
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    boolean existsByName(String name);
    Optional<Product> findByUsdaNumber(String usdaNumber);
    boolean existsByUsdaNumber(String usdaNumber);

}
