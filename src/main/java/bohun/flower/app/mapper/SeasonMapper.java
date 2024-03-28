package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.season.CreateSeasonRequestDto;
import bohun.flower.app.dto.season.SeasonDto;
import bohun.flower.app.model.Season;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface SeasonMapper {
    SeasonDto toDoSeason(Season season);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Season toEntity(CreateSeasonRequestDto createSeasonRequestDto);
}
