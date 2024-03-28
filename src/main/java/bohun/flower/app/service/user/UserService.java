package bohun.flower.app.service.user;

import bohun.flower.app.dto.user.UserOnlyMailRequestDto;
import bohun.flower.app.dto.user.UserRegistrationRequestDto;
import bohun.flower.app.dto.user.UserResponseDto;
import bohun.flower.app.model.User;
import bohun.flower.app.exception.RegistrationException;

import java.util.Optional;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException;

    UserResponseDto getUserByMail(UserOnlyMailRequestDto requestDto)
            throws RegistrationException;

    User getUserFromContext();
}
