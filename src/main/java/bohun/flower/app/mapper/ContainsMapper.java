package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.contains.ContainsDto;
import bohun.flower.app.dto.contains.CreateContainsRequestDto;
import bohun.flower.app.model.Contains;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ContainsMapper {
    ContainsDto toDoContains(Contains contains);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Contains toEntity(CreateContainsRequestDto createContainsRequestDto);
}
