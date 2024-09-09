package nbe1.team08.gridscircles.order.service.dto;

import java.util.List;
import java.util.UUID;

public record OrderCreateCommand(
        String email,
        String address,
        String postcode,
        List<OrderItemCreate> orderItemCreates
) {

    public record OrderItemCreate(
            int quantity,
            UUID productId
    ) {
    }
}
