package toyproject.shopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shopping.domain.Address;
import toyproject.shopping.domain.Member;
import toyproject.shopping.domain.valuetype.AddressType;
import toyproject.shopping.dto.AddressDto;
import toyproject.shopping.repository.AddressRepository;
import toyproject.shopping.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    /**
     * 배송지등록
     */
    public Long createAddress(Long memberId, AddressDto addressDto) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("변경");
                });
        Address savedAddress = addressRepository.save(
                new Address(addressDto.getRecipient(), getAddressType(addressDto), addressDto.getPhone(), findMember));
        return savedAddress.getId();
    }

    /**
     * 배송지조회
     */
    public List<AddressDto> searchAddress(Long memberId) {
        return addressRepository.findByMemberId(memberId).stream()
                .map(AddressDto::new)
                .toList();
    }

    /**
     * 배송지수정
     */
    public void updateAddress(Long addressId, AddressDto addressDto) {
        Address findAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("변경");
                });
        //값 타입은 통째로 갈아 끼우기
        findAddress.change(addressDto.getRecipient(), getAddressType(addressDto), addressDto.getPhone());
    }

    /**
     * 배송지삭제
     */
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    private AddressType getAddressType(AddressDto addressDto) {
        return new AddressType(addressDto.getAddress().getZipcode(),
                addressDto.getAddress().getMainAddress(), addressDto.getAddress().getSubAddress());
    }
}
