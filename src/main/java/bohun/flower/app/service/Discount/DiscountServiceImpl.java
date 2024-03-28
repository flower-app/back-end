package bohun.flower.app.service.Discount;

import bohun.flower.app.dto.discount.CreateDiscountRequestDto;
import bohun.flower.app.dto.discount.DiscountDto;
import bohun.flower.app.mapper.DiscountMapper;
import bohun.flower.app.model.Discount;
import bohun.flower.app.repository.params.DiscountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Override
    public List<DiscountDto> findAll(Pageable pageable) {
        return discountRepository.findAll(pageable).stream()
                .map(discountMapper::toDoDiscount)
                .collect(Collectors.toList());
    }

    @Override
    public DiscountDto save(CreateDiscountRequestDto createDiscountRequestDto) {
        return discountMapper.toDoDiscount(discountRepository
                .save(discountMapper.toEntity(createDiscountRequestDto)));
    }

    @Override
    public DiscountDto getDiscountById(Long discountId) {
        return discountMapper.toDoDiscount(discountRepository.findById(discountId).orElseThrow(
                () -> new EntityNotFoundException("Can't get discount with id: " + discountId)));
    }

    @Override
    public DiscountDto updateDiscount(Long discountId, CreateDiscountRequestDto createDiscountRequestDto) {
        if (!discountRepository.existsById(discountId)) {
            throw new EntityNotFoundException("Can't found discount with id: " + discountId);
        }
        Discount discount = discountMapper.toEntity(createDiscountRequestDto);
        discount.setId(discountId);
        return discountMapper.toDoDiscount(discountRepository.save(discount));
    }

    @Override
    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }
}
