package bohun.flower.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE sizes SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "sizes")
@Transactional
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "sizes")
    private List<Product> products;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted = false;
}
