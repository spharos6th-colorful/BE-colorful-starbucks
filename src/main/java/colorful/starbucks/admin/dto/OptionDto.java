package colorful.starbucks.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OptionDto {

    private List<SizeDto> size;
    private List<ColorDto> color;

    @Builder
    private OptionDto(List<SizeDto> size, List<ColorDto> color) {
        this.size = size;
        this.color = color;
    }

    public static OptionDto from(List<SizeDto> size, List<ColorDto> color) {
        return OptionDto.builder()
                .size(size)
                .color(color)
                .build();
    }
}
