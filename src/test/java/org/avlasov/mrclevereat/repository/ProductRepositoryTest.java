package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.Product;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By artemvlasov on 29/12/2017
 **/
class ProductRepositoryTest extends RepositoryTestCase<ProductRepository, Product> {

    @Autowired
    ProductRepositoryTest(ProductRepository mongoRepository) {
        super(mongoRepository);
    }


    @Test
    void save_WithExistingName_ThrowException() {
        Product entity = getEntity();
        entity.setUsdaNumber("04999");
        assertThrows(DuplicateKeyException.class, () -> mongoRepository.save(entity));
    }

    @Test
    void save_WithExistingUsdaNumber_ThrowException() {
        Product entity = getEntity();
        entity.setName("test2");
        assertThrows(DuplicateKeyException.class, () -> mongoRepository.save(entity));
    }

    @Test
    void findByName_WithExistingName_ReturnProductOptional() {
        Optional<Product> byName = mongoRepository.findByName("Chicken, broilers or fryers, meat only, raw");
        assertTrue(byName.isPresent());
        assertEquals("Chicken, broilers or fryers, meat only, raw", byName.get().getDescription());
    }

    @Test
    void findByName_WithNotExistingName_ReturnEmptyOptional() {
        Optional<Product> byName = mongoRepository.findByName("test");
        assertFalse(byName.isPresent());
    }

    @Test
    void existsByName_WithExistingName_ReturnTrue() {
        assertTrue(mongoRepository.existsByName("Chicken, broilers or fryers, meat only, raw"));
    }

    @Test
    void existsByName_WithNotExistingName_ReturnFalse() {
        assertFalse(mongoRepository.existsByName("test"));
    }

    @Test
    void findByUsdaNumber_WithExistingNumber_ReturnProductOptional() {
        Optional<Product> byUsdaNumber = mongoRepository.findByUsdaNumber("05011");
        assertTrue(byUsdaNumber.isPresent());
        assertEquals(119, byUsdaNumber.get().getNutritionalValue().getCalories());
    }

    @Test
    void findByUsdaNumber_WithNotExistingNumber_ReturnEmptyOptional() {
        Optional<Product> byUsdaNumber = mongoRepository.findByUsdaNumber("04999");
        assertFalse(byUsdaNumber.isPresent());
    }

    @Test
    void existsByUsdaNumber_WithExistingNumber_ReturnTrue() {
        assertTrue(mongoRepository.existsByUsdaNumber("05011"));
    }

    @Test
    void existsByUsdaNumber_WithNotExistingNumber_ReturnFalse() {
        assertFalse(mongoRepository.existsByUsdaNumber("04999"));
    }

    @Override
    Product getEntity() {
        Product product = new Product();
        product.setName("Chicken, broilers or fryers, meat only, raw");
        product.setDescription("Chicken, broilers or fryers, meat only, raw");
        product.setUsdaNumber("05011");
        NutritionalValue nutritionalValue = new NutritionalValue();
        nutritionalValue.setCalories(119);
        nutritionalValue.setCarbohydrate(0);
        nutritionalValue.setFat(3.08);
        nutritionalValue.setProtein(21.39);
        product.setNutritionalValue(nutritionalValue);
        product.setId(ObjectId.get());
        return product;
    }
}