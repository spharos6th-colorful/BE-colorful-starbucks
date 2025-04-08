package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.coupon.domain.MemberCoupon;
import colorful.starbucks.coupon.domain.QCoupon;
import colorful.starbucks.coupon.dto.request.MemberCouponRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static colorful.starbucks.coupon.domain.QCoupon.*;
import static colorful.starbucks.coupon.domain.QMemberCoupon.memberCoupon;

@Repository
@RequiredArgsConstructor
public class MemberCouponRepositoryCustomImpl implements MemberCouponRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    @Override
    public CursorPage<MemberCoupon> getAllCouponsByMemberUuid(MemberCouponRequestDto memberCouponRequestDto) {

        int pageSize = memberCouponRequestDto.getSize() != null ? memberCouponRequestDto.getSize() : DEFAULT_PAGE_SIZE;
        int offset = 0;
        BooleanBuilder builder = new BooleanBuilder();

        Long cursor = memberCouponRequestDto.getCursor();
        if (cursor != null) {
            builder.and(memberCoupon.id.loe(cursor));
        } else {
            int currentPage = memberCouponRequestDto.getPage() != null ? memberCouponRequestDto.getPage() : DEFAULT_PAGE_NUMBER;
            offset = currentPage == 0 ? 0 : (currentPage) * pageSize;
        }

        List<MemberCoupon> content = queryFactory.selectFrom(memberCoupon)
                .innerJoin(coupon)
                .on(coupon.couponUuid.eq(memberCoupon.couponUuid))
                .where(
                        builder,
                        eqMemberUuid(memberCouponRequestDto.getMemberUuid()),
                        coupon.expiredAt.gt(LocalDateTime.now()),
                        memberCoupon.isUsed.eq(false)
                )
                .offset(offset)
                .limit(pageSize + 1)
                .orderBy(memberCoupon.id.desc())
                .fetch();

        boolean hasNext = false;
        Long nextCursor = null;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getId();
            hasNext = true;
            content.remove(pageSize);
        }

        return CursorPage.<MemberCoupon>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    private BooleanExpression eqMemberUuid(String memberUuid) {
        return StringUtils.hasText(memberUuid) ? memberCoupon.memberUuid.eq(memberUuid) : null;
    }
}
