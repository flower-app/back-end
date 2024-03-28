package bohun.flower.app.dto.color;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ColorDto {
    private Long id;

    private String name;

    private String description;
}
