package toyproject.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.shopping.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
