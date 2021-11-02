package fi.dev.academy.vaccinationdatabase.web_controller_rest;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IOrderDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Autowired
    private IOrderDAO orderRepository;
    @Autowired
    private OrderModelAssembler assembler;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void findByOrderedAmpuleBottleId() {
        List<Order> orders = orderRepository.findByOrderedAmpuleBottleId("1251aa6c-ebaf-4e33-89d3-d6f210497b94");
        Assertions.assertThat(orders).hasSize(1);
    }
}