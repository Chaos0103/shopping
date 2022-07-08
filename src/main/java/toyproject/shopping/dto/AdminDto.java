package toyproject.shopping.dto;

import lombok.Data;

@Data
public class AdminDto {

    private Long id;
    private String loginId;
    private String password;

    public AdminDto(Long id, String loginId, String password) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
    }
}
