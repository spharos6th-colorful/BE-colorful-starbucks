package colorful.starbucks.auth.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuth {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Comment("로그인 타입")
    @Enumerated(EnumType.STRING)
    private SignType signType;

    @Comment("소셜별 사용자 고유 ID")
    @Column(nullable = false, unique = true)
    private String providerId;

    @Comment("회원 UUID")
    @Column(nullable = true)
    private String memberUuid;

    @Builder
    private OAuth(Long id,
                  SignType signType,
                  String providerId,
                  String memberUuid) {
        this.id = id;
        this.signType = signType;
        this.providerId = providerId;
        this.memberUuid = memberUuid;
    }
}
