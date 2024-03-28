package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.color.ColorDto;
import bohun.flower.app.dto.color.CreateColorRequestDto;
import bohun.flower.app.model.Color;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ColorMapper {
    ColorDto toDoColor(Color color);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Color toEntity(CreateColorRequestDto categoryDto);
}
