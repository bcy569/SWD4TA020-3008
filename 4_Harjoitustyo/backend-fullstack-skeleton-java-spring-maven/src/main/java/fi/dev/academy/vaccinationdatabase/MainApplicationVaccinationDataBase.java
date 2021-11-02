package fi.dev.academy.vaccinationdatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.dev.academy.vaccinationdatabase.common_components.utilis.SetUpDB;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IPersonDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"fi.dev.academy.vaccinationdatabase"})
public class MainApplicationVaccinationDataBase {

    @Autowired
    private SetUpDB setUpDB;

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(MainApplicationVaccinationDataBase.class, args);
    }


    // CommandLineRunner will be executed right after the startup so it is a good place to
    // do some initial setups like saving initial data into DataBase
    @Bean
//    public CommandLineRunner loadDataBase() {
        public CommandLineRunner loadDataBase(IPersonDAO userRepository) {
        return (args) -> {

//setUpDB.initDB();

            // TODO Enforce Swagger it self to ask API Key

/*
            Person admin = new Person("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ROLE_ADMIN");
            admin.setRole("ROLE_ADMIN");

            Person user = new Person("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "ROLE_USER");
            user.setRole("ROLE_USER");

            userRepository.save(admin);
            userRepository.save(user);
*/

        };
    }


}