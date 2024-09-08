package nbe1.team08.gridscircles.product.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nbe1.team08.gridscircles.common.response.Success;
import nbe1.team08.gridscircles.product.controller.dto.ProductCreate;
import nbe1.team08.gridscircles.product.controller.dto.ProductUpdate;
import nbe1.team08.gridscircles.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Success<?>> create(
            @RequestBody ProductCreate productCreate
    ) {
        var uuid = productService.create(productCreate.to());
        return ResponseEntity.ok()
                .body(Success.of(200, uuid));
    }

    @PutMapping("/products")
    public ResponseEntity<Success<?>> update(
            @RequestBody ProductUpdate productUpdate
    ) {
        var updatedProduct = productService.update(productUpdate.to());
        return ResponseEntity.ok()
                .body(Success.of(200, updatedProduct));
    }

    @DeleteMapping("/products/{uuid}")
    public ResponseEntity<Success<?>> deleteById(
            @PathVariable UUID uuid
    ) {
        productService.delete(uuid);
        return ResponseEntity.ok()
                .body(Success.of(200, "Delete Product success"));
    }
}
