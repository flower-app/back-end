package bohun.flower.app.service.shoppingCart;

import bohun.flower.app.dto.cartitem.CreateCartItemRequestDto;
import bohun.flower.app.dto.shoppingcart.ShoppingCartDto;
import bohun.flower.app.mapper.CartItemMapper;
import bohun.flower.app.mapper.ShoppingCartMapper;
import bohun.flower.app.model.CartItem;
import bohun.flower.app.model.Product;
import bohun.flower.app.model.User;
import bohun.flower.app.repository.shoppingcart.ShoppingCartRepository;
import bohun.flower.app.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import bohun.flower.app.exception.EntityNotFoundException;
import bohun.flower.app.model.ShoppingCart;
import bohun.flower.app.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void addItem(CreateCartItemRequestDto createCartItemRequestDto) {
        ShoppingCart shoppingCart = getShoppingCartForUser();
        Product product = productRepository.findById(createCartItemRequestDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find product with this id:"
                        + createCartItemRequestDto.getProductId()));
        CartItem cartItem = cartItemMapper.toCartItem(createCartItemRequestDto);
        cartItem.setProduct(product);
        cartItem.setShoppingCart(shoppingCart);
        shoppingCart.getCartItems().add(cartItem);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartDto getShoppingCart() {
        ShoppingCart shoppingCart = getShoppingCartForUser();
        return shoppingCartMapper.toDoShoppingCart(shoppingCart);
    }

    private ShoppingCart getShoppingCartForUser() {
        User user = userService.getUserFromContext();
        if (shoppingCartRepository.existsShoppingCartByUser(user)) {
            return shoppingCartRepository.findShoppingCartByUser(user)
                    .orElseThrow(() -> new EntityNotFoundException("Can't find the shopping cart"));
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }
}
