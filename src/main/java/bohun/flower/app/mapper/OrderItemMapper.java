package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.orderitem.OrderItemDto;
import bohun.flower.app.model.CartItem;
import bohun.flower.app.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.id", target = "productId")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    OrderItem convertCartItemToOrderItem(CartItem cartItem);
}
