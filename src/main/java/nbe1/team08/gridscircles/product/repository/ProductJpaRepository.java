package nbe1.team08.gridscircles.product.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nbe1.team08.gridscircles.product.domain.Product;
import nbe1.team08.gridscircles.product.service.port.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepository implements ProductRepository {

    private final ProductJpaRepositoryImpl productJpaRepositoryImpl;

    @Override
    public List<Product> findAll() {
        return productJpaRepositoryImpl.findAll();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productJpaRepositoryImpl.findById(id);
    }

    @Override
    public UUID save(Product product) {
        return productJpaRepositoryImpl.save(product)
                .getId();
    }

    @Override
    public Product update(Product product) {
        return productJpaRepositoryImpl.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        productJpaRepositoryImpl.deleteById(id);
    }
}
