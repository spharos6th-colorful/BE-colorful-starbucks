package colorful.starbucks.auth.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private Long id;

    @Column(nullable = false, name = "is_required")
    private boolean required;

    @Column(nullable = false, length = 50)
    private String termsTitle;

    @Column(nullable = false, length = 500)
    private String termsContent;

    @Builder
    private Terms(Long id,
                  boolean required,
                  String termsTitle,
                  String termsContent) {
        this.id = id;
        this.required = required;
        this.termsTitle = termsTitle;
        this.termsContent = termsContent;
    }
}
