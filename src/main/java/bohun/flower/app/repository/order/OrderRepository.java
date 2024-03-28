package bohun.flower.app.repository.order;

import bohun.flower.app.model.Order;
import bohun.flower.app.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByUser(User user, Pageable pageable);
}
