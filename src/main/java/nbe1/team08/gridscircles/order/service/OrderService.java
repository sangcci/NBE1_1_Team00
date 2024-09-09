package nbe1.team08.gridscircles.order.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nbe1.team08.gridscircles.common.exception.AccessDeniedException;
import nbe1.team08.gridscircles.order.domain.Order;
import nbe1.team08.gridscircles.order.domain.OrderItem;
import nbe1.team08.gridscircles.order.domain.Orderer;
import nbe1.team08.gridscircles.order.service.dto.OrderCancelCommand;
import nbe1.team08.gridscircles.order.service.dto.OrderCreateCommand;
import nbe1.team08.gridscircles.order.service.dto.OrderCreateCommand.OrderItemCreate;
import nbe1.team08.gridscircles.order.service.dto.OrderDetailResponse;
import nbe1.team08.gridscircles.order.service.port.OrderRepository;
import nbe1.team08.gridscircles.product.domain.Product;
import nbe1.team08.gridscircles.product.service.port.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDetailResponse> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDetailResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<OrderDetailResponse> findAllByEmail(String email) {
        List<Order> orders = orderRepository.findAllByEmail(email);
        return orders.stream()
                .map(OrderDetailResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderDetailResponse findById(UUID uuid) {
        Order order = orderRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
        return OrderDetailResponse.from(order);
    }

    @Transactional
    public UUID createOrder(OrderCreateCommand orderCreateCommand) {
        // 주문 상품 정보 확인
        List<OrderItemCreate> orderItemCreates = orderCreateCommand.orderItemCreates();
        if (orderItemCreates.isEmpty()) {
            throw new NullPointerException("Not write item lists");
        }
        List<OrderItem> orderItems = orderItemCreates.stream()
                .map(orderItemCreate -> {
                    Product product = productRepository.findById(orderItemCreate.productId())
                            .orElseThrow(() -> new NoSuchElementException("Doesn't exist product"));
                    return new OrderItem(
                            0L,
                            product.getCategory(),
                            product.getPrice(),
                            orderItemCreate.quantity(),
                            null,
                            product);
                })
                .toList();
        // 주문자 정보 확인
        Orderer orderer = new Orderer(
                orderCreateCommand.email(),
                orderCreateCommand.address(),
                orderCreateCommand.postcode()
        );
        // 주문 생성
        Order order = Order.create(orderer, orderItems);

        // 영속화
        return orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(OrderCancelCommand orderCancelCommand) {
        // 주문 존재 확인
        Order order = orderRepository.findById(orderCancelCommand.uuid())
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
        // 주문 데이터가 자신의 것이 아닐 경우 예외 발생
        if (!order.getOrderer().isMine(orderCancelCommand.email())) {
            throw new AccessDeniedException("Can't access order");
        }
        // 배송 중일 경우 예외 발생
        if (!order.canCancelOrder()) {
            throw new IllegalStateException("Can't cancel order cause already delivering");
        }

        order.cancel();
    }
}
