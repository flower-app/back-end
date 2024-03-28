package bohun.flower.app.service.color;

import bohun.flower.app.dto.color.ColorDto;
import bohun.flower.app.dto.color.CreateColorRequestDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ColorService {
    ColorDto save(CreateColorRequestDto createColorRequestDto);
    List<ColorDto> findAll(Pageable pageable);

    ColorDto getColorById(Long colorId);

    ColorDto updateColor(Long colorId, CreateColorRequestDto createColorRequestDto);

    void deleteColor(Long colorId);
}
