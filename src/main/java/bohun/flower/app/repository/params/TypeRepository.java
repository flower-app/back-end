package bohun.flower.app.repository.params;

import bohun.flower.app.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>, JpaSpecificationExecutor<Type> {
    Set<Type> findTypesByIdIn(List<Long> ids);
}
