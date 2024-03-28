package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.discount.CreateDiscountRequestDto;
import bohun.flower.app.dto.discount.DiscountDto;
import bohun.flower.app.model.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface DiscountMapper {
    DiscountDto toDoDiscount(Discount discount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Discount toEntity(CreateDiscountRequestDto createDiscountRequestDto);
}
