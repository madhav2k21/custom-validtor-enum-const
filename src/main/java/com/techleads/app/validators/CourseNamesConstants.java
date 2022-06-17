package com.techleads.app.validators;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CourseNamesConstants {

    public static final String SPRNIG_BOOT="Springboot";
    public static final String AWS="AWS";
    public static final String MICROSERVICES="Microservices";
    public static final String DATA_SCIENCE="Data Science";

    public static Collection<String> courseNames(){
        List<String> courseNames = Arrays.asList(
                SPRNIG_BOOT,
                AWS,
                MICROSERVICES,
                DATA_SCIENCE
        );
        return Collections.unmodifiableCollection(courseNames);
    }
}
