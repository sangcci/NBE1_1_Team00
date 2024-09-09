package nbe1.team08.gridscircles.order.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nbe1.team08.gridscircles.common.entity.BaseTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Embedded
    private Orderer orderer;
    @Column(name = "order_status")
    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;
    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> orderItems;

    public Order(UUID id, Orderer orderer, OrderState orderState, List<OrderItem> orderItems) {
        orderItems.forEach(orderItem -> orderItem.assignOrder(this));

        this.id = id;
        this.orderer = orderer;
        this.orderState = orderState;
        this.orderItems = orderItems;
    }

    public static Order create(Orderer orderer, List<OrderItem> orderItems) {
        return new Order(
                null,
                orderer,
                OrderState.ITEM_PREPARING,
                orderItems
        );
    }

    public void delivering() {
        this.orderState = OrderState.DELIVERING;
    }

    public boolean canCancelOrder() {
        return orderState == OrderState.ITEM_PREPARING;
    }

    public void cancel() {
        this.orderState = OrderState.CANCELED;
    }

    public long calculateTotalAmount() {
        return orderItems.stream()
                .map(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .reduce(0L, Long::sum);
    }
}
