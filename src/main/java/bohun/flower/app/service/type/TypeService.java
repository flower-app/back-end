package bohun.flower.app.service.type;

import bohun.flower.app.dto.type.CreateTypeRequestDto;
import bohun.flower.app.dto.type.TypeDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TypeService {
    List<TypeDto> findAll(Pageable pageable);
    TypeDto save(CreateTypeRequestDto createTypeRequestDto);
    TypeDto getTypeById(Long typeId);
    TypeDto updateType(Long typeId, CreateTypeRequestDto createColorRequestDto);
    void deleteType(Long colorId);
}
