package bohun.flower.app.dto.size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class CreateSizeRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
