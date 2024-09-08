package nbe1.team08.gridscircles.product.controller.dto;

import java.util.UUID;
import nbe1.team08.gridscircles.product.domain.Product;

public record ProductUpdate(
        UUID uuid,
        String name,
        String category,
        long price,
        String description,
        String imageUrl
) {

    public Product to() {
        return new Product(
                uuid,
                name,
                category,
                price,
                description,
                imageUrl
        );
    }
}
