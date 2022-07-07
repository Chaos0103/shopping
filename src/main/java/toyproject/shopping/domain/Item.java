package toyproject.shopping.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Item extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private String name;
    private int price;
    private int stockQuantity;
    private int discount;
}
