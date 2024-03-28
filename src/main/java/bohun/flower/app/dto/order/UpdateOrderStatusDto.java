package bohun.flower.app.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import bohun.flower.app.model.Status;

@Data
public class UpdateOrderStatusDto {
    @NotNull
    private Status status;
}
