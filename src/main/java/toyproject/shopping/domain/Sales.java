package toyproject.shopping.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject.shopping.domain.enumtype.PaymentStatus;
import toyproject.shopping.domain.enumtype.PaymentType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sales extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "sales_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int profit;
    private int count;

    @Enumerated(EnumType.STRING)
    private PaymentType payment;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
