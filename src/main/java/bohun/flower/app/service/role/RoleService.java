package bohun.flower.app.service.role;

import bohun.flower.app.model.Role;

public interface RoleService {
    Role save(Role role);

    boolean exists(Role.RoleName roleName);
}
