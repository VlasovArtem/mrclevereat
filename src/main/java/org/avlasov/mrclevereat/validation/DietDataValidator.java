package org.avlasov.mrclevereat.validation;

import org.avlasov.mrclevereat.entity.diet.DietData;
import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.utils.TargetWeightCalculator;
import org.avlasov.mrclevereat.validation.enums.DietDataErrorCode;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * Created By artemvlasov on 20/01/2018
 **/
@Component
public class DietDataValidator {

    private TargetWeightCalculator targetWeight;

    public DietDataValidator(TargetWeightCalculator targetWeight) {
        this.targetWeight = targetWeight;
    }

    public ValidationResult validate(User user) {
        Objects.requireNonNull(user);
        DietData dietData = user.getDietData();
        if (!(dietData.getActivityScore() > 0 && dietData.getActivityScore() <= 10)) {
            return new ValidationResult(true, DietDataErrorCode.ACTIVITY_SCORE.getCode(), null);
        } else if (!(dietData.getGramsPerWeek() >= 100 && dietData.getGramsPerWeek() <= 2000)) {
            return new ValidationResult(true, DietDataErrorCode.GRAMS_PER_WEEK.getCode(), null);
        } else {
            double targetWeightData = targetWeight.calculateTargetWeightByBrokkFormula(user);
            if (user.getDietData().getTargetWeight() < targetWeightData - 10) {
                return new ValidationResult(true, DietDataErrorCode.TARGET_WEIGHT.getCode(), new Object[]{targetWeightData});
            }
        }
        return ValidationResult.NO_ERRORS;
    }

}
