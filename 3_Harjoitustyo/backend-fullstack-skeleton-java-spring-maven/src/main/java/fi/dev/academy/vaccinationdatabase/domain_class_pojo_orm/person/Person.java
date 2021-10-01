//package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.person;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "PERSONS", schema = "PUBLIC")
//@Data
//public class Person {
//
//    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
//
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private long id;
//
//    @Column(unique=true)
//    // https://www.baeldung.com/jpa-unique-constraints
//    private String username;
//
//    @JsonIgnore // Do not print to console, into logs, or export through JSON serialization
//    private String passwordHash;
//
//    private LocalDateTime created, lastTimeEdited;
//    private String familyName, givenName, name, nickname, telephone, email;
//
//    private String[] roles;
//    private Boolean recordDeleted;
//
//    public void setPassword(String password) {
//        this.passwordHash = PASSWORD_ENCODER.encode(password);
//    }
//
///*    public void checkPassword(String password) { (3)
//        return PASSWORD_(this.passwordHash, password);
//    }*/
//}
