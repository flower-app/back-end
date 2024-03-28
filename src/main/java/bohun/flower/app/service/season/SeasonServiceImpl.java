package bohun.flower.app.service.season;

import bohun.flower.app.dto.season.CreateSeasonRequestDto;
import bohun.flower.app.dto.season.SeasonDto;
import bohun.flower.app.mapper.SeasonMapper;
import bohun.flower.app.model.Season;
import bohun.flower.app.repository.params.SeasonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    @Override
    public List<SeasonDto> findAll(Pageable pageable) {
        return seasonRepository.findAll(pageable).stream()
                .map(seasonMapper::toDoSeason)
                .collect(Collectors.toList());
    }

    @Override
    public SeasonDto save(CreateSeasonRequestDto createSeasonRequestDto) {
        return seasonMapper.toDoSeason(seasonRepository
                .save(seasonMapper.toEntity(createSeasonRequestDto)));
    }

    @Override
    public SeasonDto getSeasonById(Long seasonId) {
        return seasonMapper.toDoSeason(seasonRepository.findById(seasonId).orElseThrow(
                () -> new EntityNotFoundException("Can't get season with id: " + seasonId)));
    }

    @Override
    public SeasonDto updateSeason(Long seasonId, CreateSeasonRequestDto createSeasonRequestDto) {
        if (!seasonRepository.existsById(seasonId)) {
            throw new EntityNotFoundException("Can't found season with id: " + seasonId);
        }
        Season season = seasonMapper.toEntity(createSeasonRequestDto);
        season.setId(seasonId);
        return seasonMapper.toDoSeason(seasonRepository.save(season));
    }

    @Override
    public void deleteSeason(Long seasonId) {
        seasonRepository.deleteById(seasonId);
    }
}
