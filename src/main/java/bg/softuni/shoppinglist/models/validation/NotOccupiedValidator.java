package bg.softuni.shoppinglist.models.validation;

import bg.softuni.shoppinglist.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotOccupiedValidator implements ConstraintValidator<NotOccupied, String> {
    private final UserRepository userRepository;

    public NotOccupiedValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userRepository.findByUsername(value).isEmpty();
    }
}
