package colorful.starbucks.cart.domain;


import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@SQLDelete(sql = "Update cart SET is_deleted = true WHERE cart_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(nullable = false)
    @Comment("회원 uuid")
    private String memberUuid;

    @Column(nullable = false)
    @Comment("체크 여부")
    private boolean checked;

    @Column(nullable = false)
    @Comment("수량")
    private int quantity;

    @Column(nullable = false)
    @Comment("상품 상세 코드")
    private Long productDetailCode;

    @Column(nullable = true)
    @Comment("각인 내용")
    private String carvingContent;

    @Column(nullable = false)
    @Comment("상품 코드")
    private Long productCode;

    @Builder
    private Cart(Long id,
                 String memberUuid,
                 boolean checked,
                 int quantity,
                 Long productDetailCode,
                 String carvingContent,
                 Long productCode) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.checked = checked;
        this.quantity = quantity;
        this.productDetailCode = productDetailCode;
        this.carvingContent = carvingContent;
        this.productCode = productCode;
    }

    public void updateProductOption(Long productDetailCode, int quantity) {
        this.productDetailCode = productDetailCode;
        this.quantity = quantity;
    }

    public void updateProductChecked(boolean checked) {
        this.checked = checked;
    }

    public void addQuantity(int newQuantity) {
        this.quantity = newQuantity + this.quantity;
    }
}
