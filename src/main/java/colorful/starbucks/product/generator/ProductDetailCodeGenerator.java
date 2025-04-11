package colorful.starbucks.product.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDetailCodeGenerator {

    public static Long generate() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
