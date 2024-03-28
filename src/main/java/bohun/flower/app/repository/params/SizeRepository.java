package bohun.flower.app.repository.params;

import bohun.flower.app.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long>, JpaSpecificationExecutor<Size> {
    Set<Size> findSizesByIdIn(List<Long> ids);
}
