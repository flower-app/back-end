package bohun.flower.app.dto.order;

import lombok.Data;
import bohun.flower.app.dto.orderitem.OrderItemDto;
import bohun.flower.app.model.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private List<OrderItemDto> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Status status;
}
