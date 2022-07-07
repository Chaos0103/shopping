package toyproject.shopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;

    public Long memberJoin(MemberDto memberDto) {
        memberValidation(memberDto.getEmail(), memberDto.getPhone());
        Member savedMember = memberRepository.save(new Member(memberDto.getEmail(), memberDto.getPassword(), memberDto.getName(), memberDto.getPhone()));
        return savedMember.getId();
    }

    public Long sellerJoin(SellerDto sellerDto) {
        sellerValidation(sellerDto.getLoginId());
        Seller savedSeller = sellerRepository.save(new Seller(sellerDto.getLoginId(), sellerDto.getPassword(), sellerDto.getName(), sellerDto.getEmail(), sellerDto.getPhone(), sellerDto.getStoreName()));
        return savedSeller.getId();
    }

    public Long adminJoin(AdminDto adminDto) {
        adminValidation(adminDto.getLoginId());
        Admin savedAdmin = adminRepository.save(new Admin(adminDto.getLoginId(), adminDto.getPassword()));
        return savedAdmin.getId();
    }

    private void memberValidation(String email, String phone) {
        Optional<Member> findMember = memberRepository.memberValidation(email, phone);
        if (findMember.isPresent()) {
            throw new JoinException("이미 등록된 회원입니다.");
        }
    }

    private void sellerValidation(String loginId) {
        Optional<Seller> findSeller = sellerRepository.findByLoginId(loginId);
        if (findSeller.isPresent()) {
            throw new JoinException("이미 등록된 아이디입니다.");
        }
    }

    private void adminValidation(String loginId) {
        Optional<Admin> findAdmin = adminRepository.findByLoginId(loginId);
        if (findAdmin.isPresent()) {
            throw new JoinException("이미 등록된 아이디입니다.");
        }
    }
}
