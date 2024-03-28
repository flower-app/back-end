package bohun.flower.app.dto.product;

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ProductDto {
    private Long id;

    private Set<Long> colorIds;

    private Set<Long> typeIds;

    private Set<Long> seasonIds;

    private Set<Long> containsIds;

    private Set<Long> discountsIds;

    private Set<Long> sizesIds;

    private String name;

    private String product_name_Id;

    private String isbn;

    private BigDecimal price;

    private String description;

    private String coverImage;
}