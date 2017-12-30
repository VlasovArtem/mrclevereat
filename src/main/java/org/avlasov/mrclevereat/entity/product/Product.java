package org.avlasov.mrclevereat.entity.product;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 * USDA Number is number from website https://ndb.nal.usda.gov/ndb/search/list?fgcd=Branded+Food+Products+Database&ds=Branded+Food+Products
 * API - https://ndb.nal.usda.gov/ndb/doc/
 **/
@Entity
@Table(indexes = {
        @Index(name = "NAME_IDX", columnList = "name"),
        @Index(name = "USDANUM_IDX", columnList = "usdaNumber")
})
public class Product extends Base {

    @Column(unique = true)
    private String name;
    private String description;
    @Embedded
    private NutritionalValue nutritionalValue;
    @Column(unique = true)
    private String usdaNumber;

    private Product() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public String getUsdaNumber() {
        return usdaNumber;
    }

    public static ProductBuilder builder(String name) {
        return new ProductBuilder(name);
    }

    public static class ProductBuilder {

        private String name;
        private String description;
        private NutritionalValue nutritionalValue;
        private String usdaNumber;

        public ProductBuilder(String name) {
            this.name = name;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder nutritionalValue(NutritionalValue nutritionalValue) {
            this.nutritionalValue = nutritionalValue;
            return this;
        }

        public ProductBuilder usdaNumber(String usdaNumber) {
            this.usdaNumber = usdaNumber;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.name = name;
            product.description = description;
            product.nutritionalValue = nutritionalValue;
            product.usdaNumber = usdaNumber;
            return product;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(nutritionalValue, product.nutritionalValue) &&
                Objects.equals(usdaNumber, product.usdaNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, nutritionalValue, usdaNumber);
    }
}
