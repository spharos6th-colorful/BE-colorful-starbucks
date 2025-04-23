package colorful.starbucks.order.dto.request;

import colorful.starbucks.order.vo.request.PreOrderRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PreOrderRequestDto {

    private String memberUuid;
    private Integer totalAmount;
    private String memberAddressUuid;
    private String buyer;

    @Builder
    private PreOrderRequestDto(String memberUuid, Integer totalAmount, String memberAddressUuid, String buyer) {
        this.memberUuid = memberUuid;
        this.totalAmount = totalAmount;
        this.memberAddressUuid = memberAddressUuid;
        this.buyer = buyer;
    }

    public static PreOrderRequestDto of(PreOrderRequestVo preOrderRequestVo, String memberUuid) {
        return PreOrderRequestDto.builder()
                .memberUuid(memberUuid)
                .totalAmount(preOrderRequestVo.getTotalAmount())
                .memberAddressUuid(preOrderRequestVo.getMemberAddressUuid())
                .buyer(preOrderRequestVo.getReceiverName())
                .build();
    }
}
