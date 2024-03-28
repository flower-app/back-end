package bohun.flower.app.service.Discount;

import bohun.flower.app.dto.discount.CreateDiscountRequestDto;
import bohun.flower.app.dto.discount.DiscountDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscountService {
    List<DiscountDto> findAll(Pageable pageable);

    DiscountDto save(CreateDiscountRequestDto createColorRequestDto);

    DiscountDto getDiscountById(Long colorId);

    DiscountDto updateDiscount(Long colorId, CreateDiscountRequestDto createColorRequestDto);

    void deleteDiscount(Long colorId);
}
