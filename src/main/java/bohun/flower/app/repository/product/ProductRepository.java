package bohun.flower.app.repository.product;

import bohun.flower.app.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>  {
    List<Product> findAllByColorsId(Long colorId);
    List<Product> findAllByTypesId(Long typeId);
    List<Product> findAllBySeasonsId(Long seasonId);
    List<Product> findAllByContainsId(Long containsId);
    List<Product> findAllByDiscountsId(Long discountId);
    List<Product> findAllBySizesId(Long sizeId);

    @Query("from Product b where b.price between ?1 and ?2")
    List<Product> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);
}
