package bohun.flower.app.service.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import bohun.flower.app.dto.product.ProductDtoWithOutParams;
import bohun.flower.app.model.Color;
import bohun.flower.app.model.Contains;
import bohun.flower.app.model.Discount;
import bohun.flower.app.model.Product;
import bohun.flower.app.model.Season;
import bohun.flower.app.model.Size;
import bohun.flower.app.model.Type;
import bohun.flower.app.repository.params.DiscountRepository;
import bohun.flower.app.repository.params.ColorRepository;
import bohun.flower.app.repository.params.ContainsRepository;
import bohun.flower.app.repository.params.SeasonRepository;
import bohun.flower.app.repository.params.SizeRepository;
import bohun.flower.app.repository.params.TypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import bohun.flower.app.dto.product.ProductDto;
import bohun.flower.app.dto.product.CreateProductRequestDto;
import bohun.flower.app.exception.EntityNotFoundException;
import bohun.flower.app.mapper.ProductMapper;
import bohun.flower.app.repository.product.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ColorRepository colorRepository;
    private final TypeRepository typeRepository;
    private final SeasonRepository seasonRepository;
    private final ContainsRepository containsRepository;
    private final DiscountRepository discountRepository;
    private final SizeRepository sizeRepository;

    @Override
    @Transactional
    public ProductDto save(CreateProductRequestDto createProductRequestDto) {
        Product product = productMapper.toEntity(createProductRequestDto);
        setParameters(product, createProductRequestDto);
        return productMapper.toDtoProduct(productRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(Long productId, CreateProductRequestDto createProductRequestDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find product with id: " + productId));
        setParameters(product, createProductRequestDto);
        return productMapper.toDtoProduct(productRepository.save(product));
    }

    @Override
    public List<ProductDto> searchProducts(String keyword) {
        return productRepository.findAll().stream()
                .map(productMapper::toDtoProduct)
                .filter(book -> containsIgnoreCaseOrStartsWithWord(book.getName(), keyword)
                        || containsIgnoreCaseOrStartsWithWord(book.getDescription(), keyword))
                .collect(Collectors.toList());
    }

    private boolean containsIgnoreCaseOrStartsWithWord(String text, String keyword) {
        if (text == null || keyword == null) {
            return false;
        }
        return text.toLowerCase().contains(keyword.toLowerCase())
                || text.toLowerCase().startsWith(keyword.toLowerCase() + " ");
    }

    @Override
    public ProductDto findProductById(Long bookId) {
        return productMapper.toDtoProduct(productRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Can't get book with id: " + bookId)));
    }

    @Override
    public List<ProductDto> findAllByPriceInService(BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable) {
        List<Product> products = productRepository.findAllByPriceBetween(fromPrice, toPrice, pageable);
        return products.stream()
                .map(productMapper::toDtoProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).stream()
                .map(productMapper::toDtoProduct)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long bookId) {
        productRepository.deleteById(bookId);
    }

    @Override
    public List<ProductDtoWithOutParams> findProductsByColorId(Long colorId) {
        List<ProductDtoWithOutParams> productDtos = productRepository.findAllByColorsId(colorId)
                .stream()
                .map(productMapper::toDtoWithOutParams)
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDtoWithOutParams> findProductsByTypeId(Long typeId) {
        List<ProductDtoWithOutParams> productDtos = productRepository.findAllByTypesId(typeId)
                .stream()
                .map(productMapper::toDtoWithOutParams)
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDtoWithOutParams> findProductsBySeasonId(Long seasonId) {
        List<ProductDtoWithOutParams> productDtos = productRepository.findAllBySeasonsId(seasonId)
                .stream()
                .map(productMapper::toDtoWithOutParams)
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDtoWithOutParams> findProductsByContainsId(Long containsId) {
        List<ProductDtoWithOutParams> productDtos = productRepository.findAllByContainsId(containsId)
                .stream()
                .map(productMapper::toDtoWithOutParams)
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDtoWithOutParams> findProductsByDiscountId(Long discountId) {
        List<ProductDtoWithOutParams> productDtos = productRepository.findAllByDiscountsId(discountId)
                .stream()
                .map(productMapper::toDtoWithOutParams)
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDtoWithOutParams> findProductsBySizeId(Long sizeId) {
        List<ProductDtoWithOutParams> productDtos = productRepository.findAllBySizesId(sizeId)
                .stream()
                .map(productMapper::toDtoWithOutParams)
                .collect(Collectors.toList());
        return productDtos;
    }

    private void setParameters(Product product, CreateProductRequestDto createProductRequestDto) {
        Set<Color> colorsByIdIn = colorRepository.findColorsByIdIn(createProductRequestDto.getColorIds());
        Set<Type> typesByIdIn = typeRepository.findTypesByIdIn(createProductRequestDto.getTypeIds());
        Set<Season> seasonsByIdIn = seasonRepository.findSeasonsByIdIn(createProductRequestDto.getSeasonIds());
        Set<Contains> containsByIdIn = containsRepository.findContainsByIdIn(createProductRequestDto.getContainsIds());
        Set<Discount> discountsByIdIn = discountRepository.findDiscountsByIdIn(createProductRequestDto.getDiscountsIds());
        Set<Size> sizesIds = sizeRepository.findSizesByIdIn(createProductRequestDto.getSizesIds());

        product.setColors(colorsByIdIn);
        product.setTypes(typesByIdIn);
        product.setSeasons(seasonsByIdIn);
        product.setContains(containsByIdIn);
        product.setDiscounts(discountsByIdIn);
        product.setSizes(sizesIds);
    }
}
