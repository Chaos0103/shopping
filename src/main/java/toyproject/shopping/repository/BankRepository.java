package toyproject.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.shopping.domain.Bank;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByMemberId(Long memberId);

    Optional<Bank> findByAccount(String account);
}
