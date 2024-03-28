package bohun.flower.app.dto.contains;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ContainsDto {
    private Long id;

    private String name;

    private String description;
}
