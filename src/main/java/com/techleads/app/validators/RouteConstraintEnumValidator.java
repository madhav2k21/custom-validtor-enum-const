package com.techleads.app.validators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.techleads.app.model.Route;


public class RouteConstraintEnumValidator implements ConstraintValidator<IConstraintEnumRoute, List<Route>> {
    private List<String> acceptedValues;

    @Override
    public void initialize(IConstraintEnumRoute constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants()).map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(List<Route> value, ConstraintValidatorContext context) {


        List<String> routes = value.stream().map(v -> v.getRoutingSequenceCode()).collect(Collectors.toList());
        return null == value || acceptedValues.containsAll(routes);
    }

}
