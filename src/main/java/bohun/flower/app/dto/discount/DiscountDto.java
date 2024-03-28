package bohun.flower.app.dto.discount;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DiscountDto {
    private Long id;

    private String name;

    private String description;
}
