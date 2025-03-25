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
public class Agree extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agree_id")
    private Long id;

    private String agreeType;

    @Builder
    private Agree(Long id,
                  String agreeType) {
        this.id = id;
        this.agreeType = agreeType;
    }
}
