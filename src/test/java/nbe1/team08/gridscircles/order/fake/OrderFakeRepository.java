package nbe1.team08.gridscircles.order.fake;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import nbe1.team08.gridscircles.order.domain.Order;
import nbe1.team08.gridscircles.order.service.port.OrderRepository;

@Slf4j
public class OrderFakeRepository implements OrderRepository {

    private final Map<UUID, Order> orders = Collections.synchronizedMap(new HashMap<>());

    @Override
    public List<Order> findAll() {
        return orders.values()
                .stream()
                .toList();
    }

    @Override
    public List<Order> findAllByEmail(String email) {
        return orders.values()
                .stream()
                .filter(order -> order.getOrderer().isMine(email))
                .toList();
    }

    @Override
    public Optional<Order> findById(UUID id) {
        log.info("Find order by id: {}", orders.get(id));
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public UUID save(Order order) {
        UUID uuid = UUID.randomUUID();
        return Objects.requireNonNull(orders.put(uuid, order))
                .getId();
    }

    @Override
    public Order update(Order order) {
        return orders.put(order.getId(), order);
    }
}
