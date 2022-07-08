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
public class Admin {

    @Id @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    @Column(length = 20, updatable = false)
    private String loginId;

    @Column(length = 15)
    private String password;

    public Admin(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
