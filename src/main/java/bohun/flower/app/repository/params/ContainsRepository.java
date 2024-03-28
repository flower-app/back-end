package bohun.flower.app.repository.params;

import bohun.flower.app.model.Contains;
import bohun.flower.app.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ContainsRepository extends JpaRepository<Contains, Long>, JpaSpecificationExecutor<Contains> {
    Set<Contains> findContainsByIdIn(List<Long> ids);
}
