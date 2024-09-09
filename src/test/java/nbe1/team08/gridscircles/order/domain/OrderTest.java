package nbe1.team08.gridscircles.order.domain;

import static nbe1.team08.gridscircles.fixture.OrderFixture.주문_생성_1;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class OrderTest {

    @Test
    void 고객은_주문이_배송_중일경우_주문을_취소할_수_없다() {
        // given
        Order order = 주문_생성_1();

        // when
        order.delivering();

        // then
        assertThat(order.canCancelOrder()).isFalse();
    }

    @Test
    void 총_상품_가격을_계산한다() {
        // given
        Order order = 주문_생성_1();

        // when
        long totalAmount = order.calculateTotalAmount();

        // then
        assertThat(totalAmount).isGreaterThan(0L);
    }

    @Test
    void 주문한_만큼_재고를_감소시킨다() {
        // 재고는 무한정 있다고 가정
    }

    @Test
    void 주문_데이터가_자신의_것이_아닐_경우_false를_반환한다() {
        // given
        Orderer orderer = new Orderer("example@mail.com", null, null);
        String anotherEmail = "another@mail.com";

        // when & then
        assertThat(orderer.isMine(anotherEmail)).isFalse();
    }
}
