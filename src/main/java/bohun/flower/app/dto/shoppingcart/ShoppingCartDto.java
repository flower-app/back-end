package bohun.flower.app.dto.shoppingcart;

import bohun.flower.app.dto.cartitem.CartItemDto;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private List<CartItemDto> cartItems;
}

