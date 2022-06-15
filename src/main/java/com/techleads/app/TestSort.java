package com.techleads.app;

import java.util.Arrays;
import java.util.List;

public class TestSort {
	
	public static void main(String[] args) {
		List<String> list
		= Arrays.asList("The loadEmptyStatusCode must not be empty or blank","Load-Empty-Status-Code must be of L/E/C");
		
		list.stream().sorted((a,b)->b.compareTo(a)).forEach(System.out::println);
		
	}

}
