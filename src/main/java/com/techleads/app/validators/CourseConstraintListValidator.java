package com.techleads.app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseConstraintListValidator implements ConstraintValidator<IConstraintListCourse, String> {
	private Collection<String> acceptedValues;

	@Override
	public void initialize(IConstraintListCourse constraintAnnotation) {
		acceptedValues=CourseNamesConstants.courseNames();

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if((null==value) || (value.equals("") || value.equals("  ")||value.equals(" "))){
			return true;
		}
		return  (acceptedValues.contains(value));
	}

}
