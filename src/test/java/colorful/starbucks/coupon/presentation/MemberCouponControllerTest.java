package colorful.starbucks.coupon.presentation;

import colorful.starbucks.coupon.application.MemberCouponService;
import colorful.starbucks.coupon.application.MemberCouponServiceImpl;
import colorful.starbucks.coupon.domain.Coupon;
import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.infrastructure.CouponRepository;
import colorful.starbucks.coupon.infrastructure.MemberCouponRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberCouponControllerTest {

    @Autowired
    MemberCouponService memberCouponService;

    @Autowired
    MemberCouponRepository memberCouponRepository;

    @Autowired
    CouponRepository couponRepository;

    private MemberCouponCreateRequestDto memberCouponCreateRequestDto;

    @BeforeEach
    void setUp() {

        memberCouponRepository.deleteAll();
        memberCouponCreateRequestDto = MemberCouponCreateRequestDto.builder()
                .memberUuid("3582bff6-57da-4961-a6b2-a7221698d45e")
                .couponUuid("62eb839f-d164-4a20-9944-1e34b30a4377")
                .build();
    }

    @DisplayName("유저 쿠폰 발급 동시성 테스트")
    @Test
    void memberCouponCreateTest() throws Exception {
        //given

        //when
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    memberCouponService.createMemberCoupon(memberCouponCreateRequestDto);
                } catch (Exception e) {
                    System.out.println("수량 초과");
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await(); // 모든 스레드가 작업을 완료할 때까지 대기
        executorService.shutdown(); // 스레드 풀 종료

        //then
        Coupon coupon = couponRepository.findByCouponUuid(memberCouponCreateRequestDto.getCouponUuid())
                .orElseThrow(() -> new RuntimeException("쿠폰을 찾을 수 없습니다."));
        Assertions.assertEquals(100, memberCouponRepository.count());
        Assertions.assertEquals(100, coupon.getCurrentIssuanceCount());
    }
}