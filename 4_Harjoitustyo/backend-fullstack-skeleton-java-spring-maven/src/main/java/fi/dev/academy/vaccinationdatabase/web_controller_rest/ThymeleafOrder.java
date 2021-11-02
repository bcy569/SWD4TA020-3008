package fi.dev.academy.vaccinationdatabase.web_controller_rest;

import fi.dev.academy.vaccinationdatabase.common_components.base_model_bean.Status;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.RegisterForm;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IOrderDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IPersonDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IVaccinationDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ThymeleafOrder {
    private static final Logger log = LoggerFactory.getLogger(ThymeleafOrder.class);


    @Autowired
    private IOrderDAO orderRepository;
    @Autowired
    private IVaccinationDAO vaccinationRepository;
    @Autowired
    private IPersonDAO personRepository;


    @RequestMapping("list")
    public String getOrderList(Model model) {

        model.addAttribute("orders", orderRepository.findAll());

        return "list";

    }

    @GetMapping("thymeleafaddorder")
    public String getAddOrderForm(Model model) {

        model.addAttribute("order", new Order());

        return "add";

    }

    @PostMapping("thymeleafadd")
    public String setAdd(@ModelAttribute fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order order) {

        order.setStatus(Status.COMPLETED);
        orderRepository.save(order);

        return "redirect:/list";

    }


    @GetMapping("thymeleafedit/{id}")
    public String editOrderById(@PathVariable("id") Long id, Model model) {

        orderRepository.findById(id).ifPresent(o -> model.addAttribute("order", o));
        model.addAttribute("vaccinations", vaccinationRepository.findAll());
/*        final String[] sourceBottle = new String[1];
        optionalOrder.map(obj ->
                sourceBottle[0] = obj.getOrderedAmpuleBottleId());*/
//        model.addAttribute("releatedVaccinations", vaccinationRepository.findBySourceBottle(sourceBottle[0]));


        //FIXME
        Vaccination vaccination = new Vaccination();
        model.addAttribute("vaccination", vaccination);

        return "edit";

    }

    private Optional<Order> getOptionalOrder(Optional<Order> order) {
        return order;
    }


    @PutMapping("thymeleafupdate")
    public String putUpdate(@ModelAttribute fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order order) {

        log.info("TO BE updated order is = " + order);

        Long id = order.getId();
        order.setStatus(Status.COMPLETED);


        orderRepository.save(order);

        log.info("Updated order after saving = " + orderRepository.findById(id));

        return "redirect:/list";

    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("thymeleafdelete/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {

        orderRepository.deleteById(id);

        return "redirect:/list";

    }

    @RequestMapping(value = "register")
    public String addPerson(Model model) {
        model.addAttribute("registerform", new RegisterForm());
        return "register";
    }

    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String savePerson(@Valid @ModelAttribute("registerform") RegisterForm registerform, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {

            if (registerform.getPassword().equals(registerform.getPasswordCheck())) {

                String pwd = registerform.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                Person newUser = new Person();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(registerform.getUsername());
                newUser.setRole("ROLE_USER");
                if (personRepository.findByUsername(registerform.getUsername()) == null) {
                    personRepository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Server side validation: Käyttätunnus ei kelpaa");
                    return "register";
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Server side validation: Annetut salasanat eivät olleet yhdenmukaiset");
                return "register";
            }
        } else {
            return "register";
        }
        return "redirect:/login";
    }

}
