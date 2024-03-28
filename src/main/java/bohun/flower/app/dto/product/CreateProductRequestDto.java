package bohun.flower.app.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProductRequestDto {
    @NotEmpty
    private List<@Positive Long> colorIds;

    @NotEmpty
    private List<@Positive Long> typeIds;

    @NotEmpty
    private List<@Positive Long> seasonIds;

    @NotEmpty
    private List<@Positive Long> containsIds;

    @NotEmpty
    private List<@Positive Long> discountsIds;

    @NotEmpty
    private List<@Positive Long> sizesIds;

    @NotNull
    private String name;

    @NotNull
    private String product_name_Id;

    @NotNull
    private String isbn;

    @NotNull
    @Min(0)
    private BigDecimal price;

    private String description;

    private String coverImage;
}
