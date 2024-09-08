package nbe1.team08.gridscircles.order.controller.dto;

import java.util.List;
import nbe1.team08.gridscircles.order.domain.OrderItem;
import nbe1.team08.gridscircles.order.domain.Orderer;

public record OrderCreate(
        Orderer orderer,
        List<OrderItem> orderItems
) {
}
