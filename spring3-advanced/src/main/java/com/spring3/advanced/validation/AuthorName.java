package com.spring3.advanced.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthorNameValidator.class)
public @interface AuthorName {
	String message() default "The author name must have the follwing format : FirstName LastName";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
