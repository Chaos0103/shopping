package toyproject.shopping.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;

    public MemberDto(Long id, String email, String password, String name, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
