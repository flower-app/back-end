package bohun.flower.app.repository.params;

import bohun.flower.app.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>, JpaSpecificationExecutor<Color> {
    Set<Color> findColorsByIdIn(List<Long> ids);
}
