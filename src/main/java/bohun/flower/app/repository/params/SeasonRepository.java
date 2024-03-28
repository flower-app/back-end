package bohun.flower.app.repository.params;

import bohun.flower.app.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Repository
public interface SeasonRepository extends JpaRepository<Season, Long>, JpaSpecificationExecutor<Season> {
    Set<Season> findSeasonsByIdIn(List<Long> ids);
}
