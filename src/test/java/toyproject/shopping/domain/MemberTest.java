package toyproject.shopping.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    void changeInfo() {
        Member member = new Member("testMember@naver.com", "test1!", "member", "01011111111");

        member.changeInfo("change@naver.com", "change1!", "changeMember", "01000000000");

        assertThat(member.getEmail()).isEqualTo("change@naver.com");
    }
}