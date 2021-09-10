package fi.bcy569.domain_class_pojo_orm.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fi.bcy569.domain_class_pojo_orm.Category;

@Repository
public interface ICategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByName(String name);
}
