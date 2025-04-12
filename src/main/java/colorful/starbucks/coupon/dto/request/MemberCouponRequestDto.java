package colorful.starbucks.coupon.dto.request;

import colorful.starbucks.coupon.vo.request.MemberCouponRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCouponRequestDto {

    private String memberUuid;
    private Long cursor;
    private Integer page;
    private Integer size;
    private String sort;

    @Builder
    private MemberCouponRequestDto(String memberUuid,
                                   Long cursor,
                                   Integer page,
                                   Integer size,
                                   String sort) {
        this.memberUuid = memberUuid;
        this.cursor = cursor;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public static MemberCouponRequestDto of(String memberUuid, MemberCouponRequestVo memberCouponRequestVo) {
        return MemberCouponRequestDto.builder()
                .memberUuid(memberUuid)
                .cursor(memberCouponRequestVo.getCursor())
                .page(memberCouponRequestVo.getPage())
                .size(memberCouponRequestVo.getSize())
                .sort(memberCouponRequestVo.getSort())
                .build();
    }
}
