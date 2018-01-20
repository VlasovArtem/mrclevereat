package org.avlasov.mrclevereat.validation;

import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.repository.UserRepository;
import org.avlasov.mrclevereat.validation.enums.UserErrorCode;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created By artemvlasov on 20/01/2018
 **/
@Component
public class UserEntityValidator implements EntityValidator<User> {

    private final UserRepository userRepository;
    private final DietDataValidator dietDataValidator;

    public UserEntityValidator(UserRepository userRepository, DietDataValidator dietDataValidator) {
        this.userRepository = userRepository;
        this.dietDataValidator = dietDataValidator;
    }

    @Override
    public ValidationResult validate(User user) {
        if (!isValidEmail(user.getEmail())) {
            return ValidationResult.createErrorValidationResult(UserErrorCode.EMAIL.getCode(), new Object[]{user.getEmail()});
        } else if (userRepository.existsByEmail(user.getEmail())) {
            return ValidationResult.createErrorValidationResult(UserErrorCode.ALREADY_EXISTS.getCode(), new Object[]{user.getEmail()});
        } else if (!isValidBirthDay(user.getBirthday()) || !isValidAge(user.getAge())) {
            return ValidationResult.createErrorValidationResult(UserErrorCode.BIRTHDAY_AGE.getCode());
        } else if (!isValidWeight(user.getWeight())) {
            return ValidationResult.createErrorValidationResult(UserErrorCode.WEIGHT.getCode());
        } else if (!isValidHeight(user.getHeight())) {
            return ValidationResult.createErrorValidationResult(UserErrorCode.HEIGHT.getCode());
        } else {
            return dietDataValidator.validate(user);
        }
    }

    private boolean isValidHeight(short height) {
        return height >= 60 && height <= 260;
    }

    private boolean isValidWeight(double weight) {
        return weight >= 30 && weight <= 300;
    }

    private boolean isValidAge(int age) {
        return age >= 12 && age <= 80;
    }

    private boolean isValidBirthDay(LocalDate birthDay) {
        return Objects.nonNull(birthDay)
                && isValidAge(LocalDate.now().getYear() - birthDay.getYear());
    }

    private boolean isValidEmail(String email) {
        return Objects.nonNull(email)
                && (email.length() >= 3 && email.length() <= 30)
                && email.matches(".*@.*");
    }
}
