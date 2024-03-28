package bohun.flower.app.service.contains;

import bohun.flower.app.dto.contains.ContainsDto;
import bohun.flower.app.dto.contains.CreateContainsRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContainsService {
    List<ContainsDto> findAll(Pageable pageable);

    ContainsDto save(CreateContainsRequestDto createContainsRequestDto);

    ContainsDto getContainsById(Long containsId);

    ContainsDto updateContains(Long containsId, CreateContainsRequestDto createContainsRequestDto);

    void deleteContains(Long colorId);
}
