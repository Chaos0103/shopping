package toyproject.shopping.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {

    @Id @GeneratedValue
    @Column(name = "seller_id")
    private Long id;

    @Column(length = 20, updatable = false)
    private String loginId;
    @Column(length = 15)
    private String password;
    @Column(length = 50, updatable = false)
    private String name;
    private String email;
    @Column(length = 11)
    private String phone;
    private String storeName;

    public Seller(String loginId, String password, String name, String email, String phone, String storeName) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.storeName = storeName;
    }
}
