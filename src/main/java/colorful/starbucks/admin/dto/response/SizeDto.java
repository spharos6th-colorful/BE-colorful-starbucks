package colorful.starbucks.admin.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SizeDto {

    private Long sizeId;
    private String sizeName;

    @Builder
    private SizeDto(Long sizeId, String sizeName) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
    }

    public static SizeDto from(Long sizeId, String sizeName) {
        return SizeDto.builder()
                .sizeId(sizeId)
                .sizeName(sizeName)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SizeDto sizeDto = (SizeDto) o;
        return Objects.equals(sizeId, sizeDto.sizeId) && Objects.equals(sizeName, sizeDto.sizeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizeId, sizeName);
    }
}
