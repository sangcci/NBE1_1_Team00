package nbe1.team08.gridscircles.order.controller.dto;

import java.util.UUID;

public record OrderCancel(
        UUID uuid,
        String email
) {
}
