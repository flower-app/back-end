package bohun.flower.app.dto.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRequestTypeDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
