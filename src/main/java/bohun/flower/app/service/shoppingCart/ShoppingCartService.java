package bohun.flower.app.service.shoppingCart;

import bohun.flower.app.dto.cartitem.CreateCartItemRequestDto;
import bohun.flower.app.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    void addItem(CreateCartItemRequestDto createCartItemRequestDto);
    ShoppingCartDto getShoppingCart();
}
