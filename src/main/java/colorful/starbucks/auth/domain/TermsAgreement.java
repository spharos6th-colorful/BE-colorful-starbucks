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
public class TermsAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_agree_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;

    private boolean isAgreed;

    private String memberUuid;

    @Builder
    private TermsAgreement(Long id,
                           Terms terms,
                           boolean isAgreed,
                           String memberUuid) {
        this.id = id;
        this.terms = terms;
        this.isAgreed = isAgreed;
        this.memberUuid = memberUuid;
    }
}
