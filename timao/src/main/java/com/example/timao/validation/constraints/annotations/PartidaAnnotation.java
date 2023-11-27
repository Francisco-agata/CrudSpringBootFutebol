package com.example.timao.validation.constraints.annotations;

import com.example.timao.validation.constraints.validators.PartidaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PartidaValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface PartidaAnnotation {

    String message() default "o horário deve ser entre 08:00 e 22:00 horas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
