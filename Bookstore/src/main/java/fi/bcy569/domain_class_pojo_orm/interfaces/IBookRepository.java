package fi.bcy569.domain_class_pojo_orm.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fi.bcy569.domain_class_pojo_orm.Book;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {

	List<Book> findByTitle(String title);
}
