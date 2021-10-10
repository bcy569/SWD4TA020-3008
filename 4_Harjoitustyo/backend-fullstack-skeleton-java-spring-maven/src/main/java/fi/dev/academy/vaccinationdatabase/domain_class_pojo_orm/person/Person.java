package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PERSONS", schema = "PUBLIC")
@Data
public class Person {

    public Person() {
        super();
    }

    // TODO Remember to remove me
    // Just for testing with CommandLineRunner
    public Person(String username, String passwordHash, String role_admin) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }


    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable=false, unique=true)
    // https://www.baeldung.com/jpa-unique-constraints
    private String username;

    @JsonIgnore // Do not print to console, into logs, or export through JSON serialization
    @Column(nullable=false, name="password")
    private String passwordHash;

//    @Column(nullable=false)
//    private String[] roles;
    private String role;

    private LocalDateTime created, lastTimeEdited;
    private String familyName, givenName, name, nickname, telephone, email;

    private Boolean recordDeleted;

    public void setPassword(String password) {
        this.passwordHash = PASSWORD_ENCODER.encode(password);
    }


}
