package toyproject.shopping.domain.valuetype;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressType {

    private String zipcode;
    private String mainAddress;
    private String subAddress;

    public AddressType(String zipcode, String mainAddress, String subAddress) {
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
    }
}
