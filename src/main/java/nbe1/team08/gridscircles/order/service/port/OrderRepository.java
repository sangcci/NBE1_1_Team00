package nbe1.team08.gridscircles.order.service.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nbe1.team08.gridscircles.order.domain.Order;

public interface OrderRepository {

    List<Order> findAll();

    List<Order> findAllByEmail(String email);

    Optional<Order> findById(UUID id);

    UUID save(Order order);

    Order update(Order product);
}
