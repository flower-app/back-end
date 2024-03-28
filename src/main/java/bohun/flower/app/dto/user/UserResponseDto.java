package bohun.flower.app.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private long id;
    private String email;
    private String numberPhone;
    private String firstName;
    private String lastName;
}
