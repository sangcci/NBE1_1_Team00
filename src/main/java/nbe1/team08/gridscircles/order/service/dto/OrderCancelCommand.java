package nbe1.team08.gridscircles.order.service.dto;

import java.util.UUID;

public record OrderCancelCommand(
        UUID uuid,
        String email
) {
}
