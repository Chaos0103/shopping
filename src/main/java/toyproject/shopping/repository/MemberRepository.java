package toyproject.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.shopping.domain.Member;
import toyproject.shopping.repository.custom.MemberRepositoryCustom;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
