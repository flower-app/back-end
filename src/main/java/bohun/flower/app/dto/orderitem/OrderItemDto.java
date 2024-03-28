package bohun.flower.app.dto.orderitem;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long productId;
    private int quantity;
}
