package nbe1.team08.gridscircles.order.controller;

import lombok.RequiredArgsConstructor;
import nbe1.team08.gridscircles.common.response.Success;
import nbe1.team08.gridscircles.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<Success<?>> findAll() {
        var orders = orderService.findAll();
        return ResponseEntity.ok()
                .body(Success.of(200, orders));
    }
}
