package colorful.starbucks.cart.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class CartGetListRequestDto {

    private String memberUuid;
    private Pageable pageable;

    @Builder
    private CartGetListRequestDto(String memberUuid, Pageable pageable) {
        this.memberUuid = memberUuid;
        this.pageable = pageable;
    }

    public static CartGetListRequestDto from(String memberUuid, Pageable pageable) {
        return CartGetListRequestDto.builder()
                .memberUuid(memberUuid)
                .pageable(pageable)
                .build();
    }
}
