package com.example.DisplayProducts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DisplayProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisplayProductsApplication.class, args);

		/*DynamoClient dbClient = new DynamoClient();
		System.out.println(dbClient.queryTable("deal-wishlist", "userId", "user@yahoo.com", "#a").get(0).get("productTitle"));*/

	}
}