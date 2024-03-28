package bohun.flower.app.service.type;

import bohun.flower.app.dto.type.CreateTypeRequestDto;
import bohun.flower.app.dto.type.TypeDto;
import bohun.flower.app.mapper.TypeMapper;
import bohun.flower.app.model.Type;
import bohun.flower.app.repository.params.TypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TypeServiceImpl implements TypeService{
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    @Override
    public List<TypeDto> findAll(Pageable pageable) {
        return typeRepository.findAll(pageable).stream()
                .map(typeMapper::toDoType)
                .collect(Collectors.toList());
    }

    @Override
    public TypeDto save(CreateTypeRequestDto createTypeRequestDto) {
        return typeMapper.toDoType(typeRepository
                .save(typeMapper.toEntity(createTypeRequestDto)));
    }

    @Override
    public TypeDto getTypeById(Long typeId) {
        return typeMapper.toDoType(typeRepository.findById(typeId).orElseThrow(
                () -> new EntityNotFoundException("Can't get type with id: " + typeId)));
    }

    @Override
    public TypeDto updateType(Long typeId, CreateTypeRequestDto createTypeRequestDto) {
        if (!typeRepository.existsById(typeId)) {
            throw new EntityNotFoundException("Can't found type with id: " + typeId);
        }
        Type type = typeMapper.toEntity(createTypeRequestDto);
        type.setId(typeId);
        return typeMapper.toDoType(typeRepository.save(type));
    }

    @Override
    public void deleteType(Long typeId) {
        typeRepository.deleteById(typeId);
    }
}


