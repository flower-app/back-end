package bohun.flower.app.service.season;

import bohun.flower.app.dto.season.CreateSeasonRequestDto;
import bohun.flower.app.dto.season.SeasonDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SeasonService {
    List<SeasonDto> findAll(Pageable pageable);

    SeasonDto save(CreateSeasonRequestDto createSeasonRequestDto);

    SeasonDto getSeasonById(Long seasonId);

    SeasonDto updateSeason(Long seasonId, CreateSeasonRequestDto createSeasonRequestDto);

    void deleteSeason(Long seasonId);
}
