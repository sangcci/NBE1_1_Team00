package nbe1.team08.gridscircles.fixture;

import java.util.UUID;
import nbe1.team08.gridscircles.product.domain.Product;

@SuppressWarnings("NonAsciiCharacters")
public final class ProductFixture {

    public static Product 상품_생성_1() {
        return new Product(
                UUID.randomUUID(),
                "맥심",
                "믹스커피",
                1000,
                "우리들의 국민 커피",
                "테스트 이미지 url1"
        );
    }

    public static Product 상품_생성_2() {
        return new Product(
                UUID.randomUUID(),
                "레스비",
                "믹스커피",
                2000,
                "우리들의 국민 커피2",
                "테스트 이미지 url2"
        );
    }

}
