package bohun.flower.app.repository.shoppingcart;

import bohun.flower.app.model.ShoppingCart;
import bohun.flower.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    boolean existsShoppingCartByUser(User user);
    Optional<ShoppingCart> findShoppingCartByUser(User user);
}
