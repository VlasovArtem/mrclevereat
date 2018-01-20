package org.avlasov.mrclevereat.validation.enums;

/**
 * Created By artemvlasov on 20/01/2018
 **/
public enum  DietDataErrorCode {

    ACTIVITY_SCORE("diet.data.activity.score"),
    GRAMS_PER_WEEK("diet.data.grams.per.week"),
    TARGET_WEIGHT("diet.data.target.weight");

    private String code;

    DietDataErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
