package toyproject.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.shopping.domain.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByMemberId(Long memberId);
}
