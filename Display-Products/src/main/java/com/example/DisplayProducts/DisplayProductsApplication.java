package com.example.DisplayProducts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DisplayProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisplayProductsApplication.class, args);
		DynamoClient dc = new DynamoClient();
		System.out.println(dc.queryTable("deal-wishlist", "userid", "whatever", "#a"));
	}
}