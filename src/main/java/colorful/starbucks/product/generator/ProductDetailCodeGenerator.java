package colorful.starbucks.product.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDetailCodeGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }
}
