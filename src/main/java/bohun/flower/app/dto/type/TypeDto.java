package bohun.flower.app.dto.type;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TypeDto {
    private Long id;

    private String name;

    private String description;
}
