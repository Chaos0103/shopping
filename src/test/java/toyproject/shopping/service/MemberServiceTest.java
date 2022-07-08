package toyproject.shopping.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toyproject.shopping.domain.Member;
import toyproject.shopping.dto.MemberDto;
import toyproject.shopping.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void changeMemberInfo() {
        Member member = memberRepository.save(new Member("testMember@naver.com", "test1!", "member", "01011111111"));

        MemberDto changeMemberDto = new MemberDto(member.getId(), "change@naver.com", "change1!", "changeMember", "01000000000");
        memberService.changeInfo(member.getId(), changeMemberDto);

        Member findMember = memberRepository.findById(member.getId()).get();
        assertThat(findMember.getEmail()).isEqualTo("change@naver.com");
    }
}