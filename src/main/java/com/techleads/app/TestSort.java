package com.techleads.app;

import com.techleads.app.validators.CourseNamesConstants;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestSort {
	
	public static void main(String[] args) {

		Collection<String> strings = CourseNamesConstants.courseNames();
		List<String> first2 = strings.stream().sorted(Comparator.reverseOrder())
				.limit(2).collect(Collectors.toList());


		first2.forEach(System.out::println);
		System.out.println("==========");
		List<String> last2 = strings.stream().sorted(String::compareTo)
				.limit(2).collect(Collectors.toList());
		last2.forEach(System.out::println);
		System.exit(0);

		List<String> list
		= Arrays.asList("The loadEmptyStatusCode must not be empty or blank","Load-Empty-Status-Code must be of L/E/C");
		
		list.stream().sorted((a,b)->b.compareTo(a)).forEach(System.out::println);
		
	}

}
