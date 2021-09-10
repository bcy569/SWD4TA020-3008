package fi.bcy569.domain_class_pojo_orm;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity // ORM map into DB table
@Table(name = "CATEGORYS") //@Table(name = "BOOKS", schema = "PUBLIC")
@Data // Lombok for constructors, getters and setters
public class Category {

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> books;
	
//	public List<Book> getBooks() {
//		return books;
//	}
//
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}

}
