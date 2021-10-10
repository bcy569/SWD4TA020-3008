package fi.dev.academy.vaccinationdatabase.web_controller_rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import fi.dev.academy.vaccinationdatabase.common_components.base_model_bean.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

// https://spring.io/guides/tutorials/rest/
@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order) {

        EntityModel<Order> orderModel = EntityModel.of(order,
                linkTo(methodOn(fi.dev.academy.vaccinationdatabase.web_controller_rest.Order.class).getOneOrderById(String.valueOf(order.getId()))).withSelfRel(),
                linkTo(methodOn(fi.dev.academy.vaccinationdatabase.web_controller_rest.Order.class).getAllOrders()).withRel("getAllOrders"));

/*        return EntityModel.of(order,
                linkTo(methodOn(fi.dev.academy.vaccinationdatabase.web_controller_rest.Order.class).getOneOrderById(String.valueOf(order.getId()))).withSelfRel(),
                linkTo(methodOn(fi.dev.academy.vaccinationdatabase.web_controller_rest.Order.class).getAllOrders()).withRel("getAllOrders"));*/

        // These RESTful links are shown only when the order’s status is Status.IN_PROGRESS
        // Clients can achieve domain knowledge about the orders via links. This reduces coupling between client and server (loosely coupling -principle)
        if (order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(fi.dev.academy.vaccinationdatabase.web_controller_rest.Order.class).cancelAddOneOrder(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(fi.dev.academy.vaccinationdatabase.web_controller_rest.Order.class).completeAddOneOrder(order.getId())).withRel("complete"));
        }

        return orderModel;
    }

}
