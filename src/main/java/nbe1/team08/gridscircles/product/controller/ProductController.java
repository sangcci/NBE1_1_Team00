package nbe1.team08.gridscircles.product.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nbe1.team08.gridscircles.common.response.Success;
import nbe1.team08.gridscircles.product.domain.Product;
import nbe1.team08.gridscircles.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Success<?>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(Success.of(200, products));
    }

    @GetMapping("/products/{uuid}")
    public ResponseEntity<Success<?>> findById(
            @PathVariable UUID uuid
    ) {
        Product product = productService.findById(uuid);
        return ResponseEntity.ok().body(Success.of(200, product));
    }
}
