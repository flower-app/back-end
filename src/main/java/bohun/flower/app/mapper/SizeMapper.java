package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.size.CreateSizeRequestDto;
import bohun.flower.app.dto.size.SizeDto;
import bohun.flower.app.dto.type.CreateTypeRequestDto;
import bohun.flower.app.dto.type.TypeDto;
import bohun.flower.app.model.Size;
import bohun.flower.app.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface SizeMapper {
    SizeDto toDoSize(Size size);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Size toEntity(CreateSizeRequestDto createSizeRequestDto);
}
