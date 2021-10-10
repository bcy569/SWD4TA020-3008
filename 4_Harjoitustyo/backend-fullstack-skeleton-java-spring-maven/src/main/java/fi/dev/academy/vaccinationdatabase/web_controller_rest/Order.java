package fi.dev.academy.vaccinationdatabase.web_controller_rest;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.OrderNotFoundException;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IOrderDAO;
import fi.dev.academy.vaccinationdatabase.common_components.base_model_bean.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


// https://spring.io/guides/gs/rest-service-cors/#controller-method-cors-configuration
@CrossOrigin(origins = "http://localhost:3000/") // Endpoint where the frontend is running
@RestController
public class Order {
    private static final Logger log = LoggerFactory.getLogger(Order.class);

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
    @Autowired
    private IOrderDAO orderRepository;
    @Autowired
    private OrderModelAssembler assembler;



    // RESTful Crud: CREATE
    @PostMapping("/order")
// EntityModel<T> is generic container from Spring HATEOAS that includes data + collection of links
// https://spring.io/guides/tutorials/rest/

    ResponseEntity<?> addOneOrder(@RequestBody fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order order) {

        log.info("addOneOrder() Someone poked POST http://localhost:3000/order" + order);

        order.setStatus(Status.IN_PROGRESS);
        EntityModel<fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order> entityModel = assembler.toModel(orderRepository.save(order));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

     // RESTful cRud: READ
    @GetMapping("/orders")
    public Iterable<fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order> getAllOrders() {
        log.info("getAllOrders() Someone poked GET http://localhost:3000/orders");
        return orderRepository.findAll(); // Remote Procedure Call (RPC) way to do things
    }
//@GetMapping("/orders")
//// EntityModel<T> is generic container from Spring HATEOAS that includes data + collection of links
//// https://spring.io/guides/tutorials/rest/
//CollectionModel<EntityModel<fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order>> getAllOrders() {
//
//    List<EntityModel<fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order>> orders = orderRepository.findAll()
             // FIXME Cannot resolve method 'stream' in 'Iterable'
//            .stream()
//            .map(assembler::toModel) // OrderModelAssembler.toModel(order);
//            .collect(Collectors.toList());
//
//    return CollectionModel.of(orders, linkTo(methodOn(Order.class).getAllOrders()).withSelfRel());
//}
@GetMapping("/order/{id}")
EntityModel<fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order> getOneOrderById(@PathVariable String id) {

    log.info("getOneOrderById() Someone poked GET http://localhost:3000/order/" + id);

    fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order order = orderRepository.findById(Long.parseLong(id))
            .orElseThrow( () -> new OrderNotFoundException( Long.parseLong(id) ) );

    return assembler.toModel(order);
}



    // RESTful crUd: UPDATE
    @PutMapping("/order/{id}")
    ResponseEntity<?> updateOneOrder(@RequestBody fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order orderRequest, @PathVariable Long id) {

        log.info("updateOneOrder() Someone poked PUT http://localhost:3000/order/" + id + orderRequest);

        fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order updatedOrder = orderRepository.findById(id)
                .map(obj -> {
                    obj.setHealthCareDistrict(orderRequest.getHealthCareDistrict());
                    obj.setOrderNumber(orderRequest.getOrderNumber());
                    obj.setResponsiblePerson(orderRequest.getResponsiblePerson());
                    obj.setInjections(orderRequest.getInjections());
                    obj.setArrived(orderRequest.getArrived());
                    obj.setVaccine(orderRequest.getVaccine());
                    return orderRepository.save(obj);
                })
                .orElseGet(() -> {
                    // orderRequest.setId(id);
                    return orderRepository.save(orderRequest);
                });

        EntityModel<fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order> entityModel = assembler.toModel(updatedOrder);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    // RESTful cruD: DELETE
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/order/{id}")
    ResponseEntity<?> deleteOneOrder(@PathVariable String id) {

        log.info("deleteOneOrder() Someone poked DELETE http://localhost:3000/order/" + id);

        orderRepository.deleteById(Long.parseLong(id));

        return ResponseEntity.noContent().build();
    }

    /*
    Spring HATEOAS 'extras' for RESTful
    */
    @DeleteMapping("/orders/{id}/cancel")
    ResponseEntity<?> cancelAddOneOrder(@PathVariable Long id) {

        fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create() // RFC-7807 Problem, a hypermedia-supporting error container https://tools.ietf.org/html/rfc7807
                        .withTitle("Method not allowed")
                        .withDetail("You can't cancel an order that is in the " + order.getStatus() + " status"));
    }

    @PutMapping("/orders/{id}/complete")
    ResponseEntity<?> completeAddOneOrder(@PathVariable Long id) {

        fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order order = orderRepository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));

        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create() // RFC-7807 Problem, a hypermedia-supporting error container https://tools.ietf.org/html/rfc7807
                        .withTitle("Method not allowed")
                        .withDetail("You can't complete an order that is in the " + order.getStatus() + " status"));
    }

}
