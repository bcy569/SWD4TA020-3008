package fi.dev.academy.vaccinationdatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.dev.academy.vaccinationdatabase.common_components.utilis.SetUpDB;
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
    public CommandLineRunner loadDataBase() {
        //public CommandLineRunner loadDataBase(IPersonDAO userRepository) {
        return (args) -> {

//			setUpDB.initDB();

            // TODO Create some demo users with roles in PostgreSQL DataBase and then allow only "developers" to play
			//  with Swaggers endpoints (which manipulates the DataBase). Also enforce Swagger it self to ask API Key.
			//  Finally exclude Spring Data default API endpoints and allow only Swagger endpoints (with API Key)
//            Person admin = new Person();
//            Person user = new Person();


        };
    }


}