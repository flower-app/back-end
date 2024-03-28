package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.user.UserRegistrationRequestDto;
import bohun.flower.app.dto.user.UserResponseDto;
import bohun.flower.app.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(config = MapperConfig.class, componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {
    protected PasswordEncoder passwordEncoder;

    public abstract UserResponseDto toUserResponseDto(User user);

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password",
            expression = "java(passwordEncoder.encode(requestDto.getPassword()))")
    public abstract User toModel(UserRegistrationRequestDto requestDto);
}
