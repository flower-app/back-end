package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.order.CreateOrderDto;
import bohun.flower.app.dto.order.OrderDto;
import bohun.flower.app.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class,
        uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDtoOrder(Order order);

    Order toOrder(CreateOrderDto createOrderDto);
}
