package colorful.starbucks.color.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;

    private String colorName;

    private int extraFee;

    @Builder
    private Color(Long id, String colorName, int extraFee) {
        this.id = id;
        this.colorName = colorName;
        this.extraFee = extraFee;
    }
}
