package bohun.flower.app.service.user;

import bohun.flower.app.dto.user.UserOnlyMailRequestDto;
import bohun.flower.app.dto.user.UserRegistrationRequestDto;
import bohun.flower.app.dto.user.UserResponseDto;
import bohun.flower.app.mapper.UserMapper;
import bohun.flower.app.model.ShoppingCart;
import bohun.flower.app.model.User;
import bohun.flower.app.repository.role.RoleRepository;
import bohun.flower.app.repository.shoppingcart.ShoppingCartRepository;
import bohun.flower.app.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import bohun.flower.app.exception.RegistrationException;
import bohun.flower.app.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (requestDto.getEmail().startsWith("admin")) {
            User admin = userToMapper(requestDto);
            admin.setRoles(Set.of(roleRepository.findRoleByRoleName(Role.RoleName.ADMIN)));
            ShoppingCart shoppingCart = createShoppingCartForUser(admin);
            shoppingCartRepository.save(shoppingCart);
            return userMapper.toUserResponseDto(userRepository.save(admin));
        }
        User user = userToMapper(requestDto);
        user.setRoles(Set.of(roleRepository.findRoleByRoleName(Role.RoleName.USER)));
        ShoppingCart shoppingCart = createShoppingCartForUser(user);
        shoppingCartRepository.save(shoppingCart);
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserByMail(UserOnlyMailRequestDto requestDto) {
        Optional<User> userOptional = userRepository.findByEmail(requestDto.getEmail());

        if (userOptional.isEmpty()) {
            throw new RegistrationException("User not found with email: " + requestDto.getEmail());
        }
        User user = userOptional.get();

        return userMapper.toUserResponseDto(user);
    }

    @Override
    public User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    private User userToMapper(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        return userMapper.toModel(requestDto);
    }

    private ShoppingCart createShoppingCartForUser(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setCartItems(List.of());
        return shoppingCart;
    }
}
