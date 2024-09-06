package nbe1.team08.gridscircles.product.service.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nbe1.team08.gridscircles.product.domain.Product;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(UUID id);

    UUID save(Product product);

    Product update(Product product);

    void deleteById(UUID id);
}
