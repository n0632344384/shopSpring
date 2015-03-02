package com.shop.dao;

public class MvcException {

	public static void print(Exception e) {
		System.out.println("\nError message: " + e.getMessage());
		e.printStackTrace();
	}
}
