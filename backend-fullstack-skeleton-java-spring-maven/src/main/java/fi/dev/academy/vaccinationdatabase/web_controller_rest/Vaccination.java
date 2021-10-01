package fi.dev.academy.vaccinationdatabase.web_controller_rest;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IVaccinationDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// https://spring.io/guides/gs/rest-service-cors/#controller-method-cors-configuration
@CrossOrigin(origins = "http://localhost:3000/") // Endpoint where the frontend is running
@RestController
public class Vaccination {
    private static final Logger log = LoggerFactory.getLogger(Vaccination.class);


    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
    @Autowired
    private IVaccinationDAO vaccinationRepository;

    @GetMapping("/vaccinations")
    public Iterable<fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination> getAllVaccinations() {

        return vaccinationRepository.findAll();
    }

/*    @GetMapping("/vaccination/{id}")
    public fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination getOneVaccination(@PathVariable Integer id) {
//        return vaccinationRepository.findOne(id);
        return null;
    }*/

/*    @PostMapping("/vaccination")
    public void addOneOrder(@RequestBody fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination vaccination) {
        vaccinationRepository.save(vaccination);
    }*/

/*    // Update
    @PutMapping("/vaccination/{id}")
    public void updateOneVaccination(@PathVariable("vaccinationId") int id, @RequestBody fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination vaccination) {
        // Do some code
    }*/

 /*   @DeleteMapping("/vaccination/{id}")
    public ResponseEntity deleteOneVaccination(@PathVariable("vaccinationId") int id) {
        // Do some code
        return null;
    }*/
}
