package fi.bcy569.domain_class_pojo;

import lombok.Data;

@Data // Lombok for constructors, getters and setters
public class Book {

	private String title, author, isbn;
	private int year, price;

}
