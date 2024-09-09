package nbe1.team08.gridscircles.order.service.dto;

import java.util.List;
import java.util.UUID;
import nbe1.team08.gridscircles.order.domain.Order;
import nbe1.team08.gridscircles.order.domain.OrderItem;
import nbe1.team08.gridscircles.order.domain.OrderState;
import nbe1.team08.gridscircles.order.domain.Orderer;

public record OrderDetailResponse(
        UUID id,
        Orderer orderer,
        OrderState orderState,
        List<OrderItemResponse> orderItemResponses
) {

    public static OrderDetailResponse from(Order order) {
        var orderItemResponses = order.getOrderItems().stream()
                .map(OrderItemResponse::from)
                .toList();
        return new OrderDetailResponse(
                order.getId(),
                order.getOrderer(),
                order.getOrderState(),
                orderItemResponses
        );
    }

    public record OrderItemResponse(
            long seq,
            String category,
            long price,
            int quantity,
            UUID productId
    ) {

        public static OrderItemResponse from(OrderItem orderItem) {
            return new OrderItemResponse(
                    orderItem.getSeq(),
                    orderItem.getCategory(),
                    orderItem.getPrice(),
                    orderItem.getQuantity(),
                    orderItem.getProduct().getId()
            );
        }
    }
}
