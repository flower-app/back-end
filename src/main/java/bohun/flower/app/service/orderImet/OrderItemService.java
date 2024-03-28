package bohun.flower.app.service.orderImet;

import bohun.flower.app.dto.orderitem.OrderItemDto;
import bohun.flower.app.model.Order;
import bohun.flower.app.model.OrderItem;
import java.util.List;

public interface OrderItemService {
    List<OrderItem> getOrderItemsByCart(Order order);

    List<OrderItemDto> getItemsFromOrder(Long id);

    OrderItemDto getItemFromOrderById(Long orderId, Long itemId);
}
