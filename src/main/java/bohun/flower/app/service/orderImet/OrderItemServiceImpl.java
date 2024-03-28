package bohun.flower.app.service.orderImet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bohun.flower.app.dto.orderitem.OrderItemDto;
import bohun.flower.app.mapper.OrderItemMapper;
import bohun.flower.app.model.CartItem;
import bohun.flower.app.model.OrderItem;
import bohun.flower.app.repository.cartitem.CartItemRepository;
import bohun.flower.app.repository.orderitem.OrderItemRepository;
import bohun.flower.app.service.shoppingCart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import bohun.flower.app.exception.EntityNotFoundException;
import bohun.flower.app.model.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final ShoppingCartService shoppingCartService;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<OrderItem> getOrderItemsByCart(Order order) {
        return cartItemRepository.findAllByShoppingCartId(shoppingCartService
                        .getShoppingCart().getId()).stream()
                .map(this::getOrderItemFromCart)
                .peek(o -> o.setOrder(order))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getItemsFromOrder(Long id) {
        return orderItemRepository.getAllByOrderId(id).stream()
                .map(orderItemMapper::toOrderItemDto)
                .toList();
    }

    @Override
    public OrderItemDto getItemFromOrderById(Long orderId, Long itemId) {
        List<OrderItem> orderItems = orderItemRepository.getAllByOrderId(orderId);

        Optional<OrderItem> foundItem = orderItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();

        return foundItem.map(orderItemMapper::toOrderItemDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find item with id:" + itemId + " in order with id:" + orderId));
    }

    private OrderItem getOrderItemFromCart(CartItem cartItem) {
        OrderItem orderItem = orderItemMapper.convertCartItemToOrderItem(cartItem);
        orderItem.setPrice(orderItem.getProduct().getPrice()
                .multiply(new BigDecimal(orderItem.getQuantity())));
        return orderItem;
    }
}
