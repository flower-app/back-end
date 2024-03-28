package bohun.flower.app.service.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import bohun.flower.app.dto.order.CreateOrderDto;
import bohun.flower.app.dto.order.OrderDto;
import bohun.flower.app.dto.order.UpdateOrderStatusDto;
import bohun.flower.app.mapper.OrderMapper;
import bohun.flower.app.model.OrderItem;
import bohun.flower.app.model.Status;
import bohun.flower.app.repository.order.OrderRepository;
import bohun.flower.app.service.orderImet.OrderItemService;
import bohun.flower.app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import bohun.flower.app.exception.EntityNotFoundException;
import bohun.flower.app.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto save(CreateOrderDto requestDto) {
        Order order = orderMapper.toOrder(requestDto);
        order.setUser(userService.getUserFromContext());
        order.setCountry(requestDto.getCountry());
        order.setStreet(requestDto.getStreet());
        order.setCity(requestDto.getCity());
        order.setApartment(requestDto.getApartment());
        order.setCartNumber(requestDto.getCartNumber());
        order.setMmyy(requestDto.getMmyy());
        order.setCvvCode(requestDto.getCvvCode());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PROCESSING);

        List<OrderItem> orderItems = orderItemService.getOrderItemsByCart(order);
        BigDecimal total = orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderItems(orderItems);
        order.setTotal(total);
        return orderMapper.toDtoOrder(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> getAllOrders(Pageable pageable) {
        return orderRepository.getAllByUser(userService.getUserFromContext(),
                        pageable).stream()
                .map(orderMapper::toDtoOrder)
                .toList();
    }

    @Override
    public OrderDto updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id" + id));
        order.setStatus(updateOrderStatusDto.getStatus());
        return orderMapper.toDtoOrder(orderRepository.save(order));
    }
}
