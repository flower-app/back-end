package bohun.flower.app.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderDto {
    @NotBlank
    private String country;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String apartment;

    @NotBlank
    private String cartNumber;

    @NotBlank
    private String mmyy;

    @NotBlank
    private String cvvCode;
}
