package toyproject.shopping.domain.valuetype;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class AddressType {

    private String zipcode;
    private String mainAddress;
    private String subAddress;
}
