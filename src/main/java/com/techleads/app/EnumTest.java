package com.techleads.app;

enum Vehicles {
	// Constants with values
	ACTIVA125("80000"), ACTIVA5G("70000"), ACCESS125("75000"), VESPA("90000"), TVSJUPITER("75000");

	// Instance variable
	private final String price;

	private Vehicles(String price) {
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	// Constructor to initialize the instance variable

}

public class EnumTest {
	public static void main(String args[]) {

		Vehicles vehicles1 = Vehicles.valueOf("ACTIVA125");
		System.out.println("{} "+vehicles1.getPrice());
		System.out.println("{} "+vehicles1.name());
//		Vehicles[] values = Vehicles.values();
//		for (Vehicles vehicles : values) {
//			System.out.println(vehicles.getPrice());
//		}
	}
}