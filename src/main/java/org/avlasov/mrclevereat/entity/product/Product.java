package org.avlasov.mrclevereat.entity.product;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalFacts;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
@Document
public class Product extends Base implements NutritionalFacts {

    private String name;
    private String description;
    private NutritionalValue nutritionalValue;
    private String usdaNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public String getUsdaNumber() {
        return usdaNumber;
    }

    public void setUsdaNumber(String usdaNumber) {
        this.usdaNumber = usdaNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(nutritionalValue, product.nutritionalValue) &&
                Objects.equals(usdaNumber, product.usdaNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, nutritionalValue, usdaNumber);
    }
}
