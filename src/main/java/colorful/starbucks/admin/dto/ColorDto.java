package colorful.starbucks.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class ColorDto {

    private Long colorId;
    private String colorName;

    @Builder
    private ColorDto(Long colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public static ColorDto from(Long colorId, String colorName) {
        return ColorDto.builder()
                .colorId(colorId)
                .colorName(colorName)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ColorDto colorDto = (ColorDto) o;
        return Objects.equals(colorId, colorDto.colorId) && Objects.equals(colorName, colorDto.colorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorId, colorName);
    }
}
