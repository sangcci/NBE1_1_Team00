package nbe1.team08.gridscircles.order.repository;

import java.util.List;
import java.util.UUID;
import nbe1.team08.gridscircles.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepositoryImpl extends JpaRepository<Order, UUID> {

    List<Order> findAllByOrdererEmail(String email);
}
