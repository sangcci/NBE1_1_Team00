package nbe1.team08.gridscircles.order.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nbe1.team08.gridscircles.order.domain.Order;
import nbe1.team08.gridscircles.order.service.port.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderJpaRepository implements OrderRepository {

    private final OrderJpaRepositoryImpl orderJpaRepositoryImpl;

    @Override
    public List<Order> findAll() {
        return orderJpaRepositoryImpl.findAll();
    }

    @Override
    public List<Order> findAllByEmail(String email) {
        return orderJpaRepositoryImpl.findAllByOrdererEmail(email);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return orderJpaRepositoryImpl.findById(id);
    }

    @Override
    public UUID save(Order order) {
        return orderJpaRepositoryImpl.save(order)
                .getId();
    }

    @Override
    public Order update(Order order) {
        return orderJpaRepositoryImpl.save(order);
    }
}
