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
    public void testUserWithViolations() {
        Users user = buildUser();
        user.setLocation("");
        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertEquals(2, violations.size());
        Iterator<ConstraintViolation<Users>> iterator = violations.iterator();

        assertEquals("location",violations.iterator().next().getPropertyPath().toString());

//        System.out.println(violations.iterator().next());





//        while (iterator.hasNext()){
//            System.out.println(iterator.next().getMessage());
//        }
//        System.out.println("{} "+violations.iterator().next().getMessage());
//        assertEquals("",violations.iterator().next().getMessage());
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
