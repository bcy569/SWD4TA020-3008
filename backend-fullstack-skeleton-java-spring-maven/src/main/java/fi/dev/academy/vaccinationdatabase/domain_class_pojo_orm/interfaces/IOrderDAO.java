package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // JpaRepository
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
@PreAuthorize("hasRole('ROLE_DEVELOPER')")
public interface IOrderDAO extends CrudRepository<Order, Long> {

    List<Order> findByOrderedAmpuleBottleId(String orderedAmpuleBottleId);

}
