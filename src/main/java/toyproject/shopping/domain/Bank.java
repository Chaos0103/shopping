package toyproject.shopping.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bank {

    @Id @GeneratedValue
    @Column(name = "bank_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 13, updatable = false)
    private String account;
    @Column(length = 16, updatable = false)
    private String card;
    private int money;

    public Bank(Member member, String account, String card, int money) {
        this.member = member;
        this.account = account;
        this.card = card;
        this.money = money;
    }

    //==비즈니스 로직==//
    public void deposit(int money) {
        this.money += money;
    }

    public void withdrawal(int money) {
        if (this.money < money) {
            throw new IllegalStateException("잔액부족");
        }
        this.money -= money;
    }
}
