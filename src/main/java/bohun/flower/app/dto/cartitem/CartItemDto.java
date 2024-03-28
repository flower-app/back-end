package bohun.flower.app.dto.cartitem;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
}
