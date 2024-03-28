package bohun.flower.app.dto.contains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRequestContainsDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
