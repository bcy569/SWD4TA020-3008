package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.person.Person;
import org.springframework.data.repository.Repository;

//Maven Dependency "spring-boot-starter-data-rest"
// application.properties: 'spring.data.rest.base-path=/my_api_name'
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//@RepositoryRestResource(exported = false) // Not exposed for REST operations
public interface IPersonDAO extends Repository<Person, Long> {

    Person save(Person person);

    Person findByUsername(String username);
}
