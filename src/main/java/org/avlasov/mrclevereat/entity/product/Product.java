package org.avlasov.mrclevereat.entity.product;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.nutrition.NutritionalFacts;
import org.avlasov.mrclevereat.nutrition.NutritionalValue;

/**
 * Created By artemvlasov on 22/12/2017
 **/
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
}
