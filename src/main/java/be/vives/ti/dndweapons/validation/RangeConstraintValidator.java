package be.vives.ti.dndweapons.validation;

import be.vives.ti.dndweapons.domain.AttackRange;
import be.vives.ti.dndweapons.domain.enums.RangeType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeConstraintValidator implements
        ConstraintValidator<RangeContraint, AttackRange> {

    @Override
    public void initialize(RangeContraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AttackRange range, ConstraintValidatorContext constraintValidatorContext) {
        if (range == null) {
            return false;
        }
        Integer normalRange = range.getNormalRange();
        Integer longRange = range.getLongRange();
        if (range.getRangeType() == RangeType.MELEE || range.getRangeType() == RangeType.REACH) {
            return normalRange == null && longRange == null;
        }
        return (normalRange != null && longRange != null && longRange >= normalRange);
    }
}