package fi.bcy569.domain_class_pojo_orm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity // ORM map into DB table
@Table(name = "BOOKS") //@Table(name = "BOOKS", schema = "PUBLIC")
@Data // Lombok for constructors, getters and setters
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title, author, isbn;
	private int year, price;
}
