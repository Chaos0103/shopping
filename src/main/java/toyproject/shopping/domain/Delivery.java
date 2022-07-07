package toyproject.shopping.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject.shopping.domain.enumtype.DeliveryStatus;
import toyproject.shopping.domain.valuetype.AddressType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String recipient;

    @Embedded
    private AddressType address;

    private String phone;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
