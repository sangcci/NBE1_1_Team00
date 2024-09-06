package nbe1.team08.gridscircles.product.repository;

import java.util.UUID;
import nbe1.team08.gridscircles.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepositoryImpl extends JpaRepository<Product, UUID> {

}
