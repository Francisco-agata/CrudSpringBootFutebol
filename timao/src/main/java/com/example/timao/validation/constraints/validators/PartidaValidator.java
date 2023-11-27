package com.example.timao.validation.constraints.validators;
import com.example.timao.validation.constraints.annotations.PartidaAnnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class PartidaValidator implements ConstraintValidator<PartidaAnnotation, LocalTime> {
    public void initialize(PartidaAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        LocalTime horaMinima = LocalTime.of(8, 0);
        LocalTime horaMaxima = LocalTime.of(22, 0);

        return !
                value.isBefore(horaMinima) && !value.isAfter(horaMaxima);
    }
}
