package toyproject.shopping.repository.custom;

import toyproject.shopping.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> memberValidation(String email, String phone);
}
