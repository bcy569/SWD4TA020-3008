package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // JpaRepository
public interface IVaccinationDAO extends CrudRepository<Vaccination, Long> {

}
