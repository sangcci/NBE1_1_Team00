package nbe1.team08.gridscircles.product.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nbe1.team08.gridscircles.common.response.Success;
import nbe1.team08.gridscircles.product.controller.dto.ProductRequestDto;
import nbe1.team08.gridscircles.product.domain.Product;
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
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductAdminController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Success<?>> create(
            @RequestBody ProductRequestDto productRequestDto
    ) {
        UUID id = productService.create(productRequestDto.to());
        return ResponseEntity.ok().body(Success.of(200, id));
    }

    @PutMapping("/products")
    public ResponseEntity<Success<?>> update(
            @RequestBody ProductRequestDto productRequestDto
    ) {
        Product updated = productService.update(productRequestDto.to());
        return ResponseEntity.ok().body(Success.of(200, updated));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Success<?>> deleteById(
            @PathVariable UUID id
    ) {
        productService.delete(id);
        return ResponseEntity.ok().body(Success.of(200, "delete success"));
    }
}
