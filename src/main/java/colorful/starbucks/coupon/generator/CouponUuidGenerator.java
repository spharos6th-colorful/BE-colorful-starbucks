package colorful.starbucks.coupon.generator;

import java.util.UUID;

public class CouponUuidGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
