package colorful.starbucks.memberagree.domain;

import colorful.starbucks.agree.domain.Agree;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberAgree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_agree_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agree_id")
    private Agree agree;

    private boolean agreeStatus;

    private String memberUuid;

    @Builder
    private MemberAgree(Long id,
                       Agree agree,
                       boolean agreeStatus,
                       String memberUuid) {
        this.id = id;
        this.agree = agree;
        this.agreeStatus = agreeStatus;
        this.memberUuid = memberUuid;
    }
}
