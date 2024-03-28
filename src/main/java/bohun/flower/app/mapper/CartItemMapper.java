package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.cartitem.CartItemDto;
import bohun.flower.app.dto.cartitem.CreateCartItemRequestDto;
import bohun.flower.app.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    CartItemDto toCartItem(CartItem cartItem);

    CartItem toCartItem(CreateCartItemRequestDto createCartItemRequestDto);
}
