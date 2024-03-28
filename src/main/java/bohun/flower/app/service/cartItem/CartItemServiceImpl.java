package bohun.flower.app.service.cartItem;

import bohun.flower.app.dto.cartitem.UpdateCartItemRequestDto;
import bohun.flower.app.model.CartItem;
import bohun.flower.app.repository.cartitem.CartItemRepository;
import lombok.RequiredArgsConstructor;
import bohun.flower.app.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public void update(UpdateCartItemRequestDto updateCartItemRequestDto, Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find cart item "
                        + "with such id:" + id));
        cartItem.setQuantity(updateCartItemRequestDto.getQuantity());
        cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
