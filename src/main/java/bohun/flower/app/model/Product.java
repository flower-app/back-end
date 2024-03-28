package bohun.flower.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE products SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "product_name_id", nullable = false)
    private String product_name_Id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_colors",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Color> colors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_types",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Type> types = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_seasons",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "season_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Season> seasons = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_contains",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "contain_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Contains> contains = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_discounts",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Discount> discounts = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Size> sizes = new HashSet<>();

    private String description;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted = false;
}
