package bohun.flower.app.service.size;

import bohun.flower.app.dto.size.CreateSizeRequestDto;
import bohun.flower.app.dto.size.SizeDto;
import bohun.flower.app.mapper.SizeMapper;
import bohun.flower.app.model.Size;
import bohun.flower.app.repository.params.SizeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;

    @Override
    public List<SizeDto> findAll(Pageable pageable) {
        return sizeRepository.findAll(pageable).stream()
                .map(sizeMapper::toDoSize)
                .collect(Collectors.toList());
    }

    @Override
    public SizeDto save(CreateSizeRequestDto createSizeRequestDto) {
        return sizeMapper.toDoSize(sizeRepository
                .save(sizeMapper.toEntity(createSizeRequestDto)));
    }

    @Override
    public SizeDto getSizeById(Long sizeId) {
        return sizeMapper.toDoSize(sizeRepository.findById(sizeId).orElseThrow(
                () -> new EntityNotFoundException("Can't get size with id: " + sizeId)));
    }

    @Override
    public SizeDto updateSize(Long sizeId, CreateSizeRequestDto createSizeRequestDto) {
        if (!sizeRepository.existsById(sizeId)) {
            throw new EntityNotFoundException("Can't found size with id: " + sizeId);
        }
        Size size = sizeMapper.toEntity(createSizeRequestDto);
        size.setId(sizeId);
        return sizeMapper.toDoSize(sizeRepository.save(size));
    }

    @Override
    public void deleteSize(Long sizeId) {
        sizeRepository.deleteById(sizeId);
    }
}


