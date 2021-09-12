package fi.bcy569.domain_class_pojo_orm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity // ORM map into DB table
@Table
@Data // Lombok for constructors, getters and setters
public class Book {
	
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String title, author, isbn;
	private int year, price;
	
    @ManyToOne
    @JoinColumn(name="categoryid")
    private Category category;
    

//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}
}
