package colorful.starbucks.size.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long id;

    private String sizeName;

    private int extraFee;

    @Builder
    private Size(Long id, String sizeName, int extraFee) {
        this.id = id;
        this.sizeName = sizeName;
        this.extraFee = extraFee;
    }
}
