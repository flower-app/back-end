package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.type.CreateTypeRequestDto;
import bohun.flower.app.dto.type.TypeDto;
import bohun.flower.app.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TypeMapper {
    TypeDto toDoType(Type type);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Type toEntity(CreateTypeRequestDto createTypeRequestDto);
}
