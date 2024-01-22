package fr.donovan.cap_entreprise.validator.annotation;

import fr.donovan.cap_entreprise.repository.EntityNameRepository;
import fr.donovan.cap_entreprise.validator.UniqueNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueName {

    Class<? extends EntityNameRepository<?>> repositoryClass();

    String message() default "This name already exists !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
