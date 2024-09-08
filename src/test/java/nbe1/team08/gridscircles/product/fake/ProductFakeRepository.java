package nbe1.team08.gridscircles.product.fake;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import nbe1.team08.gridscircles.product.domain.Product;
import nbe1.team08.gridscircles.product.service.port.ProductRepository;

@Slf4j
public class ProductFakeRepository implements ProductRepository {

    private final Map<UUID, Product> clubs = Collections.synchronizedMap(new HashMap<>());

    @Override
    public List<Product> findAll() {
        return clubs.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        log.info("Find product by id: {}", clubs.get(id));
        return Optional.ofNullable(clubs.get(id));
    }

    @Override
    public UUID save(Product product) {
        return Objects.requireNonNull(clubs.put(product.getId(), product))
                .getId();
    }

    @Override
    public Product update(Product product) {
        return clubs.put(product.getId(), product);
    }

    @Override
    public void deleteById(UUID id) {
        clubs.remove(id);
    }
}
