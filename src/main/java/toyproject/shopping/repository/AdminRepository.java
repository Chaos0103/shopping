package toyproject.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.shopping.domain.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByLoginId(String loginId);
}
