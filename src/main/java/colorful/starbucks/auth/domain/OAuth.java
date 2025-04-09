package colorful.starbucks.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
public class OAuth {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Comment("oauth_id")
    private Long id;

    @Comment("소셜 로그인 제공자")
    @Column(nullable = false)
    private String provider;

    @Comment("소셜별 사용자 고유 ID")
    @Column(nullable = false, unique = true)
    private String providerId;

    @Comment("회원 UUID")
    @Column(nullable = true)
    private String memberUuid;

    @Builder
    private OAuth(Long id,
                  String provider,
                  String providerId,
                  String memberUuid) {
        this.id = id;
        this.provider = provider;
        this.providerId = providerId;
        this.memberUuid = memberUuid;
    }
}
