package bohun.flower.app.service.product;

import java.math.BigDecimal;
import java.util.List;

import bohun.flower.app.dto.product.ProductDto;
import bohun.flower.app.dto.product.CreateProductRequestDto;
import bohun.flower.app.dto.product.ProductDtoWithOutParams;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    List<ProductDtoWithOutParams> findProductsByColorId(Long colorId);
    List<ProductDtoWithOutParams> findProductsByTypeId(Long typeId);
    List<ProductDtoWithOutParams> findProductsBySeasonId(Long seasonId);
    List<ProductDtoWithOutParams> findProductsByContainsId(Long containsId);
    List<ProductDtoWithOutParams> findProductsByDiscountId(Long discountId);
    List<ProductDtoWithOutParams> findProductsBySizeId(Long sizeId);
    ProductDto findProductById(Long id);
    ProductDto save(CreateProductRequestDto bookRequestDto);
    List<ProductDto> findAll(Pageable pageable);
    ProductDto updateProduct(Long bookId, CreateProductRequestDto bookRequestDto);
    void deleteProduct(Long bookId);
    List<ProductDto> searchProducts(String keyword);
    List<ProductDto> findAllByPriceInService(BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);
}
