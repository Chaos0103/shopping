package toyproject.shopping.domain;

import org.junit.jupiter.api.Test;
import toyproject.shopping.domain.valuetype.AddressType;

import static org.assertj.core.api.Assertions.*;

class AddressTest {

    @Test
    void change() {
        AddressType addressType = new AddressType("12345", "서울특별시", "subAddress");
        Address address = new Address("recipient", addressType, "01011111111", null);

        address.change("newRecipient", addressType, "01022222222");

        assertThat(address.getRecipient()).isEqualTo("newRecipient");
    }
}