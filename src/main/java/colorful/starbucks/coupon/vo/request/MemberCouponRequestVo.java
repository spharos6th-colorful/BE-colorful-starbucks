package colorful.starbucks.coupon.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCouponRequestVo {

    private Long cursor;
    private Integer page;
    private Integer size;
    private String sort;
}
