package org.avlasov.mrclevereat.utils;

import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.entity.user.enums.Gender;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created By artemvlasov on 20/01/2018
 **/
@Component
public class TargetWeightCalculator {

    /**
     * Calculate Target Weight by Brokk Formula
     *
     * female: (height - 110) * 1,15
     * male: (height - 100) * 1,15
     *
     * @param user user
     * @return target weight
     */
    public double calculateTargetWeightByBrokkFormula(User user) {
        Objects.requireNonNull(user);
        int genderVariable = 100;
        if (Gender.FEMALE.equals(user.getGender())) {
            genderVariable = 110;
        }
        return (user.getHeight() - genderVariable) * 1.15;
    }

}
