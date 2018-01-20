package org.avlasov.mrclevereat.validation.enums;

/**
 * Created By artemvlasov on 20/01/2018
 **/
public enum  UserErrorCode {

    ALREADY_EXISTS("user.already.exists"),
    EMAIL("user.email"),
    BIRTHDAY_AGE("user.birthday.age"),
    WEIGHT("user.weight"),
    HEIGHT("user.height");

    private String code;

    UserErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
