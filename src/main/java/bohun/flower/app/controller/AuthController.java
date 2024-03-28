package bohun.flower.app.controller;

import bohun.flower.app.dto.user.UserLoginRequestDto;
import bohun.flower.app.dto.user.UserLoginResponseDto;
import bohun.flower.app.dto.user.UserRegistrationRequestDto;
import bohun.flower.app.dto.user.UserResponseDto;
import bohun.flower.app.security.AuthenticationService;
import bohun.flower.app.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import bohun.flower.app.exception.RegistrationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/registration")
    public UserResponseDto registrationUser(@Valid @RequestBody UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
