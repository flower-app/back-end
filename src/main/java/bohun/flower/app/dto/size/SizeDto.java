package bohun.flower.app.dto.size;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SizeDto {
    private Long id;

    private String name;

    private String description;
}
