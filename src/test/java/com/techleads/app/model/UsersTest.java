package com.techleads.app.model;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    public void testUserNoViolations() {

        Users user = buildUser();
        Set<ConstraintViolation<Users>> violations = validator.validate(user);

        assertTrue(violations.isEmpty());
        assertThat(violations.size()).isEqualTo(0);
    }


    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    @ValueSource(strings = {""})
    public void testUserWith_Course_couseName_Empty_And_Null_Violations(String courseName) {
        Users user = buildUser();
        List<Courses> courses = Arrays.asList(new Courses(101, courseName));
        user.setCourses(courses);
        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a, b) -> a.getMessage().compareTo(b.getMessage()))
                .toList();
        constraintViolations.stream().forEach(v-> System.out.println("{} "+v.getMessage()));
        assertFalse(violations.isEmpty());
        assertThat(violations.size()).isEqualTo(1);
        assertThat(constraintViolations.get(0).getPropertyPath().toString()).isEqualTo("courses[0].courseName");
        assertThat(constraintViolations.get(0).getMessage()).isEqualTo("Course Name must not be empty");
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    public void testUserWith_skillsId_Empty_And_Null_Violations(Integer skillId) {
        Users user = buildUser();

        user.getSkills().setId(skillId);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a, b) -> a.getMessage().compareTo(b.getMessage()))
                .toList();
        assertThat(violations.size()).isEqualTo(1);

        assertThat(constraintViolations.get(0).getPropertyPath().toString()).isEqualTo("skills.id");
        assertThat(constraintViolations.get(0).getMessage()).isEqualTo("Please enter id");
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    @ValueSource(strings = {""})
    public void testUserWith_skillsName_Empty_And_Null_Violations(String skillName) {
        Users user = buildUser();

        user.getSkills().setSkillName(skillName);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a, b) -> a.getMessage().compareTo(b.getMessage()))
                .toList();
        assertThat(violations.size()).isEqualTo(1);

        assertThat(constraintViolations.get(0).getPropertyPath().toString()).isEqualTo("skills.skillName");
        assertThat("skillName must not be").isEqualTo(constraintViolations.get(0).getMessage());

    }


    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    @ValueSource(strings = {"", "  "})
    public void testUserWith_location_Empty_And_Null_Violations(String customValue) {
        Users user = buildUser();

        user.setLocation(customValue);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(1, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a, b) -> a.getMessage().compareTo(b.getMessage()))
                .toList();

        assertThat("location").isEqualTo(constraintViolations.get(0).getPropertyPath().toString());
        assertThat("Location field must not be empty").isEqualTo(constraintViolations.get(0).getMessage());
    }


    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("blankOrNullStrings")
    public void testUserWith_location_Null_Violations(String input) {
        Users user = buildUser();

        user.setLocation(input);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(1, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a, b) -> a.getMessage().compareTo(b.getMessage()))
                .toList();

        assertThat("location").isEqualTo(constraintViolations.get(0).getPropertyPath().toString());
        assertThat("Location field must not be empty").isEqualTo(constraintViolations.get(0).getMessage());
    }

    static Stream<String> blankOrNullStrings() {
        return Stream.of("", " ", null);
    }

    static Stream<String> acceptedLocatonCodes() {
        return Stream.of("HYD_CDE", "BLR_CDE", "CHN_CDE");
    }


    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {"madhav", "dill", "test"})
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
    @MethodSource("acceptedLocatonCodes")
    public void testUserWith_location_AcceptedValues_Violations(String customInput) {
        Users user = buildUser();

        user.setLocation(customInput);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(0, violations.size());
        assertThat(violations.isEmpty()).isTrue();


    }


    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    @MethodSource("emptyRoutes")
    public void testUserWith_routes_Empty_And_Null_Violations(List<Route> routes) {
        Users user = buildUser();
        user.setRoutes(routes);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(1, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted((a, b) -> b.getMessage().compareTo(a.getMessage()))
                .toList();

        assertEquals("routes", constraintViolations.get(0).getPropertyPath().toString());
        assertEquals("Route field must not be empty", constraintViolations.get(0).getMessage());
    }


    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("invalidRoutes")
    public void testUserWith_routes_Invalid_Codes_Violations(List<Route> routes) {
        Users user = buildUser();
        user.setRoutes(routes);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(1, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .toList();

        assertEquals("routes", constraintViolations.get(0).getPropertyPath().toString());
        assertEquals("Route code must be of A/B/C", constraintViolations.get(0).getMessage());
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("invalidCourses")
    public void testUserWith_Course_Invalid_Cources_Violations(List<Courses> courses) {
        Users user = buildUser();
        user.setCourses(courses);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(2, violations.size());

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .sorted(Comparator.comparing(a -> a.getPropertyPath().toString()))
                .toList();

        constraintViolations.forEach(v->{
            System.out.println("{} "+v.getPropertyPath().toString());
        });
        assertEquals("courses[0].courseName", constraintViolations.get(0).getPropertyPath().toString());
        assertEquals("courses[1].courseName", constraintViolations.get(1).getPropertyPath().toString());
        assertEquals("Course name must of Springboot/AWS/Miroservices", constraintViolations.get(0).getMessage());
        assertEquals("Course name must of Springboot/AWS/Miroservices", constraintViolations.get(1).getMessage());
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("validCourses")
    public void testUserWith_Course_Valid_Cources_ZeroViolations(List<Courses> courses) {
        Users user = buildUser();
        user.setCourses(courses);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(0, violations.size());
        assertThat(violations.isEmpty()).isTrue();


    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("validRoutes")
    public void testUserWith_routes_Accepted_Codes_ZeroViolations(List<Route> routes) {
        Users user = buildUser();
        user.setRoutes(routes);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertThat(violations.size()).isEqualTo(0);
        assertThat(violations.isEmpty()).isTrue();

        List<ConstraintViolation<Users>> constraintViolations = violations.stream()
                .toList();


    }

    private static Stream<Arguments> invalidRoutes() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Route(101, "test"), new Route(102, "AD"))),
                Arguments.of(Arrays.asList(new Route(103, "DA"), new Route(104, "EF")))
        );
    }

    private static Stream<Arguments> invalidCourses() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Courses(101, "test"), new Courses(102, "AD"))),
                Arguments.of(Arrays.asList(new Courses(101, "test"), new Courses(102, "AD"))),
                Arguments.of(Arrays.asList(new Courses(103, "DA"), new Courses(104, "EF"))),
                Arguments.of(Arrays.asList(new Courses(101, "test"), new Courses(102, "AD")))
        );
    }

    private static Stream<Arguments> validCourses() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Courses(101, "Springboot")
                        , new Courses(102, "Microservices"))),
                Arguments.of(Arrays.asList(new Courses(101, "Springboot")
                        , new Courses(102, "Microservices"))),
                Arguments.of(Arrays.asList(new Courses(103, "AWS"),
                        new Courses(104, "Data Science")))
        );
    }

    private static Stream<Arguments> emptyRoutes() {
        List<Route> routes = new ArrayList<>();
        return Stream.of(

                Arguments.of(routes)
        );
    }

    private static Stream<Arguments> validRoutes() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Route(101, "A"), new Route(102, "B"), new Route(102, "C"))),
                Arguments.of(Arrays.asList(new Route(103, "B"), new Route(104, "C"), new Route(102, "A")))
        );
    }

    public Users buildUser() {

        Users u = new Users();

        u.setId(101);
        u.setLocation("HYD_CDE");
        u.setName("Madhav");

        List<Route> routes = new ArrayList<>();
        routes.add(new Route(101, "A", u));
        u.setRoutes(routes);
        u.setSkills(new Skill(101,"test"));
//        List<Courses> courses = Arrays.asList(new Courses());
        List<Courses> courses = Arrays.asList(new Courses(101,"Springboot"),new Courses(102,"AWS"));
        u.setCourses(courses);

        return u;
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "abc"})
    void isBlank_ShouldReturnTrueForNonNullOrNonBlankStrings(String input) {
//        assertTrue(Strings.isBlank(input));
        assertTrue(Strings.isNotBlank(input));
    }

}
