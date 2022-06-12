package com.techleads.app.validators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocationConstraintEnumValidator implements ConstraintValidator<IConstraintEnumLocation, String> {
	private List<String> acceptedValues;

	@Override
	public void initialize(IConstraintEnumLocation constraintAnnotation) {
		acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants()).map(Enum::name)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return acceptedValues.contains(value);
	}

}
