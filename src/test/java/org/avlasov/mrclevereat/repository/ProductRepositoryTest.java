package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By artemvlasov on 29/12/2017
 **/
class ProductRepositoryTest extends RepositoryTestCase {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save_WithExistingName_ThrowException() {
        Optional<Product> byId = productRepository.findById(1L);
        if (byId.isPresent()) {
            Product.ProductBuilder entity = getEntity(byId.get());
            entity.usdaNumber("9999");
            assertThrows(DataIntegrityViolationException.class, () -> productRepository.save(entity.build()));
        }
    }

    @Test
    void save_WithExistingUsdaNumber_ThrowException() {
        Optional<Product> byId = productRepository.findById(1L);
        if (byId.isPresent()) {
            Product product = byId.get();
            Product newProduct = Product.builder("Test Name")
                    .usdaNumber(product.getUsdaNumber())
                    .description(product.getDescription())
                    .nutritionalValue(product.getNutritionalValue())
                    .build();
            assertThrows(DataIntegrityViolationException.class, () -> productRepository.save(newProduct));
        }
    }

    @Test
    void findByName_WithExistingName_ReturnProductOptional() {
        Optional<Product> byName = productRepository.findByName("tomato");
        assertTrue(byName.isPresent());
        assertEquals("tomato", byName.get().getDescription());
    }

    @Test
    void findByName_WithNotExistingName_ReturnEmptyOptional() {
        Optional<Product> byName = productRepository.findByName("test");
        assertFalse(byName.isPresent());
    }

    @Test
    void existsByName_WithExistingName_ReturnTrue() {
        assertTrue(productRepository.existsByName("carrot"));
    }

    @Test
    void existsByName_WithNotExistingName_ReturnFalse() {
        assertFalse(productRepository.existsByName("test"));
    }

    @Test
    void findByUsdaNumber_WithExistingNumber_ReturnProductOptional() {
        Optional<Product> byUsdaNumber = productRepository.findByUsdaNumber("11352");
        assertTrue(byUsdaNumber.isPresent());
        assertEquals(77, byUsdaNumber.get().getNutritionalValue().getCalories());
    }

    @Test
    void findByUsdaNumber_WithNotExistingNumber_ReturnEmptyOptional() {
        Optional<Product> byUsdaNumber = productRepository.findByUsdaNumber("04999");
        assertFalse(byUsdaNumber.isPresent());
    }

    @Test
    void existsByUsdaNumber_WithExistingNumber_ReturnTrue() {
        assertTrue(productRepository.existsByUsdaNumber("05011"));
    }

    @Test
    void existsByUsdaNumber_WithNotExistingNumber_ReturnFalse() {
        assertFalse(productRepository.existsByUsdaNumber("04999"));
    }


    private Product.ProductBuilder getEntity(String name, String description, String usdaNumber, NutritionalValue nutritionalValue) {
        return Product.builder(name)
                .nutritionalValue(nutritionalValue)
                .description(description)
                .usdaNumber(usdaNumber);
    }

    private Product.ProductBuilder getEntity(Product product) {
        return getEntity(product.getName(), product.getDescription(), product.getUsdaNumber(), product.getNutritionalValue());
    }
}