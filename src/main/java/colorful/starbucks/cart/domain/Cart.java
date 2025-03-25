package colorful.starbucks.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    private String memberUuid;
    private boolean checked;
    private int quantity;
    private String productDetailCode;

    @Builder
    private Cart(Long id,
                 String memberUuid,
                 boolean checked,
                 int quantity,
                 String productDetailCode) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.checked = checked;
        this.quantity = quantity;
        this.productDetailCode = productDetailCode;
    }
}
