package nbe1.team08.gridscircles.order.controller;

import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nbe1.team08.gridscircles.common.response.Success;
import nbe1.team08.gridscircles.order.controller.dto.OrderCancel;
import nbe1.team08.gridscircles.order.controller.dto.OrderCreate;
import nbe1.team08.gridscircles.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<Success<?>> findAllByEmail(
            @RequestParam final String email
    ) {
        var orders = orderService.findAllByEmail(email);
        return ResponseEntity.ok()
                .body(Success.of(200, orders));
    }

    @GetMapping("orders/{uuid}")
    public ResponseEntity<Success<?>> findById(
            @PathVariable final UUID uuid
    ) {
        var order = orderService.findById(uuid);
        return ResponseEntity.ok()
                .body(Success.of(200, order));
    }

    @PostMapping("/orders")
    public ResponseEntity<Success<?>> createOrder(
            @RequestBody final OrderCreate orderCreate
    ) {
        var createdUuid = orderService.createOrder(orderCreate.orderer(), orderCreate.orderItems());
        return ResponseEntity.created(URI.create("/orders/" + createdUuid))
                .body(Success.of(201, createdUuid));
    }

    @PutMapping("/orders/cancel")
    public ResponseEntity<Success<?>> cancelOrder(
            @RequestBody final OrderCancel orderCancel
    ) {
        orderService.cancelOrder(orderCancel.uuid(), orderCancel.email());
        return ResponseEntity.ok()
                .body(Success.of(202, "Delete order success"));
    }
}
