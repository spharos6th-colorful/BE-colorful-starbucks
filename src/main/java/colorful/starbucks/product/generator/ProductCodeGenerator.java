package colorful.starbucks.product.generator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ProductCodeGenerator {

    public static Long generate() {
        return (LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
