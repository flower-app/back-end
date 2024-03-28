package bohun.flower.app.service.contains;

import bohun.flower.app.dto.contains.ContainsDto;
import bohun.flower.app.dto.contains.CreateContainsRequestDto;
import bohun.flower.app.mapper.ContainsMapper;
import bohun.flower.app.model.Contains;
import bohun.flower.app.repository.params.ContainsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContainsServiceImpl implements ContainsService {
    private final ContainsRepository containsRepository;
    private final ContainsMapper containsMapper;

    @Override
    public List<ContainsDto> findAll(Pageable pageable) {
        return containsRepository.findAll(pageable).stream()
                .map(containsMapper::toDoContains)
                .collect(Collectors.toList());
    }

    @Override
    public ContainsDto save(CreateContainsRequestDto createContainsRequestDto) {
        return containsMapper.toDoContains(containsRepository
                .save(containsMapper.toEntity(createContainsRequestDto)));
    }

    @Override
    public ContainsDto getContainsById(Long containsId) {
        return containsMapper.toDoContains(containsRepository.findById(containsId).orElseThrow(
                () -> new EntityNotFoundException("Can't get contains with id: " + containsId)));
    }

    @Override
    public ContainsDto updateContains(Long containsId, CreateContainsRequestDto createContainsRequestDto) {
        if (!containsRepository.existsById(containsId)) {
            throw new EntityNotFoundException("Can't found color with id: " + containsId);
        }
        Contains contains = containsMapper.toEntity(createContainsRequestDto);
        contains.setId(containsId);
        return containsMapper.toDoContains(containsRepository.save(contains));
    }

    @Override
    public void deleteContains(Long containsId) {
        containsRepository.deleteById(containsId);
    }
}
