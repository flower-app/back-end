package bohun.flower.app.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDtoWithOutParams {
    private Long id;
    private String name;
    private String product_name_Id;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
