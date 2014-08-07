package com.spring3.advanced.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AuthorNameValidator implements
		ConstraintValidator<AuthorName, String> {

	@Override
	public void initialize(AuthorName arg0) {
	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile("[a-zA-Z]*\\s[a-zA-Z]*");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

}
