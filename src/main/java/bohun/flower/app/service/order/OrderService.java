package bohun.flower.app.service.order;

import java.util.List;

import bohun.flower.app.dto.order.CreateOrderDto;
import bohun.flower.app.dto.order.OrderDto;
import bohun.flower.app.dto.order.UpdateOrderStatusDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface OrderService {
    OrderDto save(CreateOrderDto requestDto);

    List<OrderDto> getAllOrders(@PageableDefault Pageable pageable);

    OrderDto updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto, Long id);
}
