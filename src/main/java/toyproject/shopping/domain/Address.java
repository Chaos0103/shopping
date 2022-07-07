package toyproject.shopping.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject.shopping.domain.valuetype.AddressType;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @Column(length = 50)
    private String recipient;

    @Embedded
    private AddressType address;

    @Column(length = 11)
    private String phone;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
