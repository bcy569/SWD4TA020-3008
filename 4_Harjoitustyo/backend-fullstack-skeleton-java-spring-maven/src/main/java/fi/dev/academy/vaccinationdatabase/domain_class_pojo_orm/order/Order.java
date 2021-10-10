package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination;
import fi.dev.academy.vaccinationdatabase.common_components.base_model_bean.Status;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/*
 https://en.wikipedia.org/wiki/Plain_old_Java_object
 The Bean class is a sub-set of the POJO class
 All the Bean files can be POJOs, but not all the POJOs are beans
 All Bean files can implement a Serializable interface but, not all the POJOs can implement a Serializable interface
 The POJO is used when we want to provide full access to users and restrict our members. And, the Bean is used when we want to provide partial access to the members
 https://www.javatpoint.com/pojo-in-java
*/

@Entity // JPA: ORM map into DB table
@Table(name = "ORDERS", schema = "PUBLIC")
@Data // Lombok for getters and setters
public class Order {

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    // Optimistic locking (on concurrent use prevent to update the older one)
    // https://www.baeldung.com/jpa-optimistic-locking
    // version=0 --> version=1
    // org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value
    // mapping was incorrect) : [fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order#1]
    @Version
    @JsonIgnore
    private Long version;

    private Status status; // IN_PROGRESS, COMPLETED, CANCELLED


    private String orderedAmpuleBottleId; //Vaccination ampule for doses = Vaccination.sourceBottle
    private String responsiblePerson, healthCareDistrict, vaccine;
    private int orderNumber, injections;
    private LocalDateTime arrived;

    // https://techyowls.com/post/jpa-one-to-many/
    // One ordered ampoule bottle can have many vaccination events
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whichOrderIncludesTheseVaccinationsLinked")
    // Which vaccinations has been dosed by this order (ampule for doses)
    // https://stackoverflow.com/questions/2749689/what-is-the-owning-side-in-an-orm-mapping
    private List<Vaccination> vaccinations;

//    @Override
//    public String toString() {
//        return String.format(
//                "Order[id=%s, orderNumber='%d', 'responsiblePerson'='%s', 'healthCareDistrict'='%s', 'vaccine'='%s', 'injections'='%d', 'arrived''=%s']",
//                id, orderNumber, responsiblePerson, healthCareDistrict, vaccine, injections, arrived);
//    }
}
