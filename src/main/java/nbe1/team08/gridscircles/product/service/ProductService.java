package nbe1.team08.gridscircles.product.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nbe1.team08.gridscircles.product.domain.Product;
import nbe1.team08.gridscircles.product.service.port.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Transactional
    public UUID create(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product newProduct) {
        Product product = productRepository.findById(newProduct.getId())
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        return product.update(newProduct);
    }

    @Transactional
    public void delete(UUID id) {
        productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        productRepository.deleteById(id);
    }
}
