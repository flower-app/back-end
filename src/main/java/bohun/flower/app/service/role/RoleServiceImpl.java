package bohun.flower.app.service.role;

import bohun.flower.app.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import bohun.flower.app.model.Role;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean exists(Role.RoleName roleName) {
        return roleRepository.existsByRoleName(roleName);
    }
}
