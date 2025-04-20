package colorful.starbucks.member.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("약관 필수 여부")
    @Column(nullable = false, name = "is_required")
    private boolean required;

    @Comment("약관 제목")
    @Column(nullable = false)
    private String termsTitle;

    @Comment("약관 내용")
    @Column(nullable = false)
    private String termsContent;

    @Comment("약관 카테고리")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TermsCategory termsCategory;

    @Builder
    private Terms(Long id,
                  boolean required,
                  String termsTitle,
                  String termsContent,
                  TermsCategory termsCategory) {
        this.id = id;
        this.required = required;
        this.termsTitle = termsTitle;
        this.termsContent = termsContent;
        this.termsCategory = termsCategory;
    }
}
