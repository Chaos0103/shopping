package toyproject.shopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shopping.domain.Member;
import toyproject.shopping.dto.MemberDto;
import toyproject.shopping.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 정보수정
     */
    public void changeInfo(Long memberId, MemberDto memberDto) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if(findMember.isEmpty()) {
            throw new IllegalStateException("변경");
        }
        findMember.get().changeInfo(memberDto.getEmail(), memberDto.getPassword(), memberDto.getName(), memberDto.getPhone());
    }
}
