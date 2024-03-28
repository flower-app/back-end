package bohun.flower.app.service.color;

import bohun.flower.app.dto.color.ColorDto;
import bohun.flower.app.dto.color.CreateColorRequestDto;
import bohun.flower.app.mapper.ColorMapper;
import bohun.flower.app.model.Color;
import bohun.flower.app.repository.params.ColorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    @Override
    public ColorDto save(CreateColorRequestDto createColorRequestDto) {
        return colorMapper.toDoColor(colorRepository
                .save(colorMapper.toEntity(createColorRequestDto)));
    }

    @Override
    public List<ColorDto> findAll(Pageable pageable) {
        return colorRepository.findAll(pageable).stream()
                .map(colorMapper::toDoColor)
                .collect(Collectors.toList());
    }

    @Override
    public ColorDto getColorById(Long colorId) {
        return colorMapper.toDoColor(colorRepository.findById(colorId).orElseThrow(
                () -> new EntityNotFoundException("Can't get color with id: " + colorId)));
    }

    @Override
    public ColorDto updateColor(Long colorId, CreateColorRequestDto createColorRequestDto) {
        if (!colorRepository.existsById(colorId)) {
            throw new EntityNotFoundException("Can't found color with id: " + colorId);
        }
        Color color = colorMapper.toEntity(createColorRequestDto);
        color.setId(colorId);
        return colorMapper.toDoColor(colorRepository.save(color));
    }

    @Override
    public void deleteColor(Long colorId) {
        colorRepository.deleteById(colorId);
    }
}
