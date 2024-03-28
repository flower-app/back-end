package bohun.flower.app.repository.role;

import bohun.flower.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleName(Role.RoleName roleName);
    Role findRoleByRoleName(Role.RoleName user);
}
