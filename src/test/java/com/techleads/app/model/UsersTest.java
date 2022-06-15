package com.techleads.app.model;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
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

    @ParameterizedTest
    @ValueSource(strings = {"test",})
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
//        assertTrue(Strings.isBlank(input));
        assertTrue(Strings.isNotBlank(input));
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    @ValueSource(strings = {"", "  "})
    public void testUserWith_location_Empty_Violations(String value) {
        Users user = buildUser();

        user.setLocation(value);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(2, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a, b) -> a.getMessage().compareTo(b.getMessage()))
                .toList();

        assertEquals("location", constraintViolations.get(0).getPropertyPath().toString());
        assertEquals("Location field must not be empty", constraintViolations.get(0).getMessage());
        assertEquals("Location must be of HYD/CHN/BLR", constraintViolations.get(1).getMessage());
    }


    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("blankOrNullStrings")
    public void testUserWith_location_Null_Violations(String input) {
        Users user = buildUser();

        user.setLocation(input);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(2, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a, b) -> a.getMessage().compareTo(b.getMessage()))
                .toList();

        assertEquals("location", constraintViolations.get(0).getPropertyPath().toString());
        assertEquals("Location field must not be empty", constraintViolations.get(0).getMessage());
        assertEquals("Location must be of HYD/CHN/BLR", constraintViolations.get(1).getMessage());
    }

    static Stream<String> blankOrNullStrings() {
        return Stream.of("", " ", null);
    }
    static Stream<String> acceptedLocatonCodes() {
        return Stream.of("HYD_CDE", "BLR_CDE", "CHN_CDE");
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {"madhav", "dill","test"})
    public void testUserWith_location_InvalidValues_Violations(String customInput) {
        Users user = buildUser();

        user.setLocation(customInput);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(1, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream().toList();
        assertThat(violations.size()).isEqualTo(1);

        assertEquals("location", constraintViolations.get(0).getPropertyPath().toString());
        assertEquals("Location must be of HYD/CHN/BLR", constraintViolations.get(0).getMessage().toString());
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
//    @ValueSource(strings = {"HYD_CDE", "BLR_CDE","CHN_CDE"})
    @MethodSource("acceptedLocatonCodes")
    public void testUserWith_location_AcceptedValues_Violations(String customInput) {
        Users user = buildUser();

        user.setLocation(customInput);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(0, violations.size());
        assertThat(violations.isEmpty()).isTrue();


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
