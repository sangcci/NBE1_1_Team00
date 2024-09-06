package nbe1.team08.gridscircles.service;

import static nbe1.team08.gridscircles.fixture.ProductFixture.상품_생성_1;

import java.util.NoSuchElementException;
import nbe1.team08.gridscircles.fake.ProductFakeRepository;
import nbe1.team08.gridscircles.product.domain.Product;
import nbe1.team08.gridscircles.product.service.ProductService;
import nbe1.team08.gridscircles.product.service.port.ProductRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class ProductServiceTest {

    private final ProductRepository productRepository;

    private final ProductService productService;

    public ProductServiceTest() {
        this.productRepository = new ProductFakeRepository();
        this.productService = new ProductService(
                productRepository);
    }

    @Test
    void 상품을_조회하거나_수정하거나_삭제할_때_id_값이_없다면_예외를_발생시킨다() {
        // given
        Product 상품_1 = 상품_생성_1();

        // when & then
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThatThrownBy(() -> productService.findById(상품_1.getId()))
                    .isInstanceOf(NoSuchElementException.class);
            assertions.assertThatThrownBy(() -> productService.update(상품_1))
                    .isInstanceOf(NoSuchElementException.class);
            assertions.assertThatThrownBy(() -> productService.delete(상품_1.getId()))
                    .isInstanceOf(NoSuchElementException.class);
        });
    }
}
