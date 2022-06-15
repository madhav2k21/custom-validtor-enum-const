package com.techleads.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    public void testUserNoViolations() {
        Users user = buildUser();
        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        Iterator<ConstraintViolation<Users>> itr = violations.iterator();
        assertTrue(violations.isEmpty());

    }


    @Test
    public void testUserWith_location_Empty_Violations() {
        Users user = buildUser();

        user.setLocation(null);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(2, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a,b)-> a.getMessage().compareTo(b.getMessage()))
                .toList();

        assertEquals("location", constraintViolations.get(0).getPropertyPath().toString());
        assertEquals("Location field must not be empty", constraintViolations.get(0).getMessage());
        assertEquals("Location must be of HYD/CHN/BLR", constraintViolations.get(1).getMessage());
    }
    @Test
    public void testUserWith_location_WrongValue_Violations() {
        Users user = buildUser();

        user.setLocation("test");

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(1, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream().toList();

        assertEquals("location", constraintViolations.get(0).getPropertyPath().toString());
        assertEquals("Location must be of HYD/CHN/BLR", constraintViolations.get(0).getMessage().toString());
    }

    public Users buildUser() {

        Users u = new Users();

        u.setId(101);
        u.setLocation("HYD_CDE");
        u.setName("Madhav");

        List<Route> routes = new ArrayList<>();
        routes.add(new Route(101, "A", u));
        u.setRoutes(routes);

        return u;
    }

}
