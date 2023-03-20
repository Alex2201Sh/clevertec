package by.shumilov.clevertec.bean;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Class Product represents Products.
 */
@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(setterPrefix = "set")
@Jacksonized
@NoArgsConstructor
public final class Product extends Item {
    private String name;
    private double price;
    /**
     * The field isPromotion shows whether the product is promotional.
     */
    @Column(name = "is_promotion")
    private Boolean promotion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
