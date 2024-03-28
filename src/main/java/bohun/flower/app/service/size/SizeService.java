package bohun.flower.app.service.size;

import bohun.flower.app.dto.size.CreateSizeRequestDto;
import bohun.flower.app.dto.size.SizeDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SizeService {
    List<SizeDto> findAll(Pageable pageable);
    SizeDto save(CreateSizeRequestDto createSizeRequestDto);
    SizeDto getSizeById(Long sizeId);
    SizeDto updateSize(Long sizeId, CreateSizeRequestDto createSizeRequestDto);
    void deleteSize(Long sizeId);
}
