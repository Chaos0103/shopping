package toyproject.shopping.dto;

import lombok.Data;
import toyproject.shopping.domain.Address;

@Data
public class AddressDto {

    private Long id;
    private String recipient;
    private AddressTypeDto address;
    private String phone;
    private MemberDto memberDto;

    //등록
    public AddressDto(Long id, String recipient, AddressTypeDto address, String phone) {
        this.id = id;
        this.recipient = recipient;
        this.address = address;
        this.phone = phone;
    }

    //domain -> dto
    public AddressDto(Address address) {
        this.id = address.getId();
        this.recipient = address.getRecipient();
        this.address = new AddressTypeDto(address.getAddress());
        this.phone = address.getPhone();
    }
}
