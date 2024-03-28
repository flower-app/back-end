package bohun.flower.app.dto.season;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SeasonDto {
    private Long id;

    private String name;

    private String description;
}
