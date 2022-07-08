package toyproject.shopping.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toyproject.shopping.domain.Address;
import toyproject.shopping.domain.Member;
import toyproject.shopping.domain.valuetype.AddressType;
import toyproject.shopping.dto.AddressDto;
import toyproject.shopping.dto.AddressTypeDto;
import toyproject.shopping.repository.AddressRepository;
import toyproject.shopping.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AddressServiceTest {

    @Autowired AddressService addressService;
    @Autowired AddressRepository addressRepository;
    @Autowired MemberRepository memberRepository;

    @Test
    void createAddress() {
        Member member = getMember();
        AddressTypeDto addressTypeDto = new AddressTypeDto("12345", "서울특별시", "subAddress");
        AddressDto addressDto = new AddressDto(null, "recipient", addressTypeDto, "01012345678");
        Long addressId = addressService.createAddress(member.getId(), addressDto);

        Address findAddress = addressRepository.findById(addressId).get();

        assertThat(findAddress.getId()).isEqualTo(addressId);
    }

    @Test
    void searchAddress() {
        Member member = getMember();
        for (int i = 0; i < 5; i++) {
            AddressType addressType = new AddressType("12345", "서울특별시", "subAddress");
            addressRepository.save(new Address("recipient", addressType, "01012345678", member));
        }

        List<AddressDto> addressDtoList = addressService.searchAddress(member.getId());

        assertThat(addressDtoList.size()).isEqualTo(5);
    }

    @Test
    void updateAddress() {
        Member member = getMember();
        AddressType addressType = new AddressType("12345", "서울특별시", "subAddress");
        Address savedAddress = addressRepository.save(new Address("recipient", addressType, "01012345678", member));

        AddressTypeDto addressTypeDto = new AddressTypeDto("67890", "인천광역시", "subAddress");
        AddressDto addressDto = new AddressDto(null, "recipient", addressTypeDto, "01012345678");

        addressService.updateAddress(savedAddress.getId(), addressDto);

        Address findAddress = addressRepository.findById(savedAddress.getId()).get();

        assertThat(findAddress.getAddress().getMainAddress()).isEqualTo("인천광역시");
    }

    @Test
    void deleteAddress() {
        Member member = getMember();
        AddressType addressType = new AddressType("12345", "서울특별시", "subAddress");
        Address savedAddress = addressRepository.save(new Address("recipient", addressType, "01012345678", member));

        addressService.deleteAddress(savedAddress.getId());

        Optional<Address> findAddress = addressRepository.findById(savedAddress.getId());

        assertThat(findAddress).isEmpty();
    }

    private Member getMember() {
        return memberRepository.save(new Member("testMember@naver.com", "test1!", "member", "01011111111"));
    }
}