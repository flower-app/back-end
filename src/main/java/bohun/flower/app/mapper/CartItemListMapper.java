package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.cartitem.CartItemDto;
import bohun.flower.app.model.CartItem;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(config = MapperConfig.class,
        uses = CartItemMapper.class)
public interface CartItemListMapper {
    List<CartItemDto> toCartItemDtoSet(List<CartItem> cartItemSet);
}
