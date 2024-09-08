package nbe1.team08.gridscircles.fixture;

import static nbe1.team08.gridscircles.fixture.ProductFixture.*;

import java.util.List;
import nbe1.team08.gridscircles.order.domain.Order;
import nbe1.team08.gridscircles.order.domain.OrderItem;
import nbe1.team08.gridscircles.order.domain.Orderer;

@SuppressWarnings("NonAsciiCharacters")
public class OrderFixture {

    public static Order 주문_생성_1() {
        Orderer orderer = new Orderer(
                "example@mail.com",
                "서울시",
                "우편번호 예시"
        );
        List<OrderItem> orderItems = List.of(
                new OrderItem(0,
                        "믹스커피",
                        1000,
                        5,
                        null,
                        상품_생성_1()
                ),
                new OrderItem(1,
                        "믹스커피",
                        2000,
                        3,
                        null,
                        상품_생성_2()
                )
        );
        return Order.create(orderer, orderItems);
    }
}
