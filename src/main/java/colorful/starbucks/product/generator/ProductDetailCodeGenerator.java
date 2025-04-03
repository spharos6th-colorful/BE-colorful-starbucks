package colorful.starbucks.product.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDetailCodeGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
