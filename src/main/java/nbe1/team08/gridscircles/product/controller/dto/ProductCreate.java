package nbe1.team08.gridscircles.product.controller.dto;


import nbe1.team08.gridscircles.product.domain.Product;

public record ProductCreate(
        String name,
        String category,
        long price,
        String description,
        String imageUrl
) {

    public Product to() {
        return new Product(
                null,
                name,
                category,
                price,
                description,
                imageUrl
        );
    }
}
