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
public class TermsAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_agree_id")
    private Long id;

    @Comment("약관 번호")
    @Column(nullable = false)
    private Long termsId;

    @Comment("약관 동의 여부")
    private boolean isAgreed;

    @Comment("회원 UUID")
    private String memberUuid;

    @Builder
    private TermsAgreement(Long id,
                          Long termsId,
                          boolean isAgreed,
                          String memberUuid) {
        this.id = id;
        this.termsId = termsId;
        this.isAgreed = isAgreed;
        this.memberUuid = memberUuid;
    }
}
