package colorful.starbucks.product.generator;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
public class ProductCodeGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }
}
