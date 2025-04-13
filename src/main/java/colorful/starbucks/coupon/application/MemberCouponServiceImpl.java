package colorful.starbucks.coupon.application;

import colorful.starbucks.common.aop.DistributedLock;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.dto.request.MemberCouponRequestDto;
import colorful.starbucks.coupon.dto.response.MemberCouponResponseDto;
import colorful.starbucks.coupon.infrastructure.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCouponServiceImpl implements MemberCouponService {

    private final MemberCouponRepository memberCouponRepository;
    private final CouponService couponService;

    @DistributedLock(key = "#memberCouponCreateRequestDto.getCouponUuid()")
    @Transactional
    @Override
    public void createMemberCoupon(MemberCouponCreateRequestDto memberCouponCreateRequestDto) {

        checkDuplicatedIssuance(memberCouponCreateRequestDto);
        couponService.issueCoupon(memberCouponCreateRequestDto.getCouponUuid());

        memberCouponRepository.save(memberCouponCreateRequestDto.toEntity());
    }

    @Transactional
    @Override
    public void useCoupon(String couponUuid, String memberUuid, LocalDateTime usedAt) {
        memberCouponRepository.updateMemberStateToUsed(couponUuid, memberUuid, usedAt);
    }

    @Override
    public CursorPage<MemberCouponResponseDto> getMemberCoupons(MemberCouponRequestDto memberCouponRequestDto) {
        return memberCouponRepository.getAllCouponsByMemberUuid(memberCouponRequestDto)
                .map(MemberCouponResponseDto::from);
    }

    private void checkDuplicatedIssuance(MemberCouponCreateRequestDto memberCouponCreateRequestDto) {
        boolean isDuplicatedIssuance = memberCouponRepository.existsByMemberUuidAndCouponUuid(
                memberCouponCreateRequestDto.getMemberUuid(),
                memberCouponCreateRequestDto.getCouponUuid()
        );

        if (isDuplicatedIssuance) {
            throw new BaseException(ResponseStatus.ALREADY_ISSUED_COUPON);
        }
    }
}
