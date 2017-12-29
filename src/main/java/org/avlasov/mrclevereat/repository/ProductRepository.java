package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.product.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created By artemvlasov on 29/12/2017
 **/
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    Optional<Product> findByName(String name);
    boolean existsByName(String name);
    Optional<Product> findByUsdaNumber(String usdaNumber);
    boolean existsByUsdaNumber(String usdaNumber);

}
