package bohun.flower.app.controller;

import bohun.flower.app.dto.user.UserOnlyMailRequestDto;
import bohun.flower.app.dto.user.UserResponseDto;
import bohun.flower.app.exception.RegistrationException;
import bohun.flower.app.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User management", description = "Endpoints for managing user")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/by-email")
    public UserResponseDto getUserByEmail(@Valid @RequestBody UserOnlyMailRequestDto requestDto)
            throws RegistrationException {
        return userService.getUserByMail(requestDto);
    }
}
