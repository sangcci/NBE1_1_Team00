package nbe1.team08.gridscircles.product.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nbe1.team08.gridscircles.product.domain.Product;
import nbe1.team08.gridscircles.product.service.port.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public UUID create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        return productRepository.update(product);
    }

    public void delete(UUID id) {
        productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        productRepository.deleteById(id);
    }
}
