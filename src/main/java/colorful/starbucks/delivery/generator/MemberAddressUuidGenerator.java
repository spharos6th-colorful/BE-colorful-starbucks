package colorful.starbucks.delivery.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MemberAddressUuidGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
