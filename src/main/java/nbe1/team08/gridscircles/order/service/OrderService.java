package nbe1.team08.gridscircles.order.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nbe1.team08.gridscircles.common.exception.AccessDeniedException;
import nbe1.team08.gridscircles.order.domain.Order;
import nbe1.team08.gridscircles.order.domain.OrderItem;
import nbe1.team08.gridscircles.order.domain.Orderer;
import nbe1.team08.gridscircles.order.service.port.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Order> findAllByEmail(String email) {
        return orderRepository.findAllByEmail(email);
    }

    @Transactional(readOnly = true)
    public Order findById(UUID uuid) {
        return orderRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @Transactional
    public UUID createOrder(Orderer orderer, List<OrderItem> orderItems) {
        Order order = Order.create(orderer, orderItems);

        UUID savedUuid = orderRepository.save(order);
        return savedUuid;
    }

    @Transactional
    public void cancelOrder(UUID uuid, String email) {
        Order order = orderRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));

        if (!order.getOrderer().isMine(email)) {
            throw new AccessDeniedException("Can't access order");
        }

        if (!order.canCancelOrder()) {
            throw new IllegalStateException("Can't cancel order cause already delivering");
        }
        order.cancel();
    }
}
