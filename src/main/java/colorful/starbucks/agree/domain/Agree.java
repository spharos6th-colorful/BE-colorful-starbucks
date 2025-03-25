package colorful.starbucks.agree.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agree {

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
