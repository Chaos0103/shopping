package toyproject.shopping.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shopping.domain.Admin;
import toyproject.shopping.domain.Member;
import toyproject.shopping.domain.Seller;
import toyproject.shopping.dto.AdminDto;
import toyproject.shopping.dto.MemberDto;
import toyproject.shopping.dto.SellerDto;
import toyproject.shopping.exception.JoinException;
import toyproject.shopping.repository.AdminRepository;
import toyproject.shopping.repository.MemberRepository;
import toyproject.shopping.repository.SellerRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JoinServiceTest {

    @Autowired JoinService joinService;
    @Autowired MemberRepository memberRepository;
    @Autowired SellerRepository sellerRepository;
    @Autowired AdminRepository adminRepository;

    @Test
    void joinMember() {
        MemberDto memberDto = new MemberDto(null, "testMember@naver.com", "test1!", "testMember", "01011111111");
        Long memberId = joinService.memberJoin(memberDto);

        Member findMember = memberRepository.findById(memberId).get();

        assertThat(findMember.getId()).isEqualTo(memberId);
    }

    @Test
    void joinSeller() {
        SellerDto sellerDto = new SellerDto(null, "sellerId", "test1!", "testSeller", "testSeller@naver.com", "01022222222", "store");
        Long sellerId = joinService.sellerJoin(sellerDto);

        Seller findSeller = sellerRepository.findById(sellerId).get();

        assertThat(findSeller.getId()).isEqualTo(sellerId);
    }

    @Test
    void joinAdmin() {
        AdminDto adminDto = new AdminDto(null, "adminId", "test1!");
        Long adminId = joinService.adminJoin(adminDto);

        Admin findAdmin = adminRepository.findById(adminId).get();

        assertThat(findAdmin.getId()).isEqualTo(adminId);
    }

    @Test
    void joinMemberValidationByEmail() {
        memberRepository.save(new Member("testMember@naver.com", "test1!", "member1", "01011111111"));
        MemberDto memberDto = new MemberDto(null, "testMember@naver.com", "test2@", "member2", "01022222222");

        assertThrows(JoinException.class, () -> {
            joinService.memberJoin(memberDto);
        });
    }

    @Test
    void joinMemberValidationByPhone() {
        memberRepository.save(new Member("testMember1@naver.com", "test1!", "member1", "01011111111"));
        MemberDto memberDto = new MemberDto(null, "testMember2@naver.com", "test2@", "member2", "01011111111");

        assertThrows(JoinException.class, () -> {
            joinService.memberJoin(memberDto);
        });
    }

    @Test
    void joinSellerValidation() {
        sellerRepository.save(new Seller("sellerId", "test1!", "testSeller", "testSeller1@naver.com", "01011111111", "store"));
        SellerDto sellerDto = new SellerDto(null, "sellerId", "test2@", "testSeller", "testSeller2@naver.com", "01022222222", "store");

        assertThrows(JoinException.class, () -> {
            joinService.sellerJoin(sellerDto);
        });
    }

    @Test
    void joinAdminValidation() {
        adminRepository.save(new Admin("adminId", "test1!"));
        AdminDto adminDto = new AdminDto(null, "adminId", "test2@");

        assertThrows(JoinException.class, () -> {
            joinService.adminJoin(adminDto);
        });
    }
}