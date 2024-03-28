package bohun.flower.app.service.cartItem;

import bohun.flower.app.dto.cartitem.UpdateCartItemRequestDto;

public interface CartItemService {
    void update(UpdateCartItemRequestDto updateCartItemRequestDto, Long id);

    void delete(Long id);
}
