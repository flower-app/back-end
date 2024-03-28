package bohun.flower.app.mapper;

import bohun.flower.app.config.MapperConfig;
import bohun.flower.app.dto.product.ProductDto;
import bohun.flower.app.dto.product.CreateProductRequestDto;
import bohun.flower.app.dto.product.ProductDtoWithOutParams;
import bohun.flower.app.model.Color;
import bohun.flower.app.model.Contains;
import bohun.flower.app.model.Discount;
import bohun.flower.app.model.Product;
import bohun.flower.app.model.Season;
import bohun.flower.app.model.Size;
import bohun.flower.app.model.Type;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.stream.Collectors;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    ProductDto toDtoProduct(Product product);
    ProductDtoWithOutParams toDtoWithOutParams(Product product);
    @AfterMapping
    default void setParamsIds(@MappingTarget ProductDto productDto, Product product) {
        productDto.setColorIds(product.getColors()
                .stream()
                .map(Color::getId)
                .collect(Collectors.toSet()));

        productDto.setTypeIds(product.getTypes()
                .stream()
                .map(Type::getId)
                .collect(Collectors.toSet()));

        productDto.setSeasonIds(product.getSeasons()
                .stream()
                .map(Season::getId)
                .collect(Collectors.toSet()));

        productDto.setContainsIds(product.getContains()
                .stream()
                .map(Contains::getId)
                .collect(Collectors.toSet()));

        productDto.setDiscountsIds(product.getDiscounts()
                .stream()
                .map(Discount::getId)
                .collect(Collectors.toSet()));

        productDto.setSizesIds(product.getSizes()
                .stream()
                .map(Size::getId)
                .collect(Collectors.toSet()));
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Product toEntity(CreateProductRequestDto productDto);
}
