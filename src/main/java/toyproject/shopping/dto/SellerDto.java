package toyproject.shopping.dto;

import lombok.Data;

@Data
public class SellerDto {

    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String storeName;

    public SellerDto(Long id, String loginId, String password, String name, String email, String phone, String storeName) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.storeName = storeName;
    }
}
