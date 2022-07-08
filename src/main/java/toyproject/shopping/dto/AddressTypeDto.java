package toyproject.shopping.dto;

import lombok.Data;
import toyproject.shopping.domain.valuetype.AddressType;

@Data
public class AddressTypeDto {

    private String zipcode;
    private String mainAddress;
    private String subAddress;

    public AddressTypeDto(String zipcode, String mainAddress, String subAddress) {
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
    }

    public AddressTypeDto(AddressType addressType) {
        this.zipcode = addressType.getZipcode();
        this.mainAddress = addressType.getMainAddress();
        this.subAddress = addressType.getSubAddress();
    }
}
