package colorful.starbucks.product.generator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ProductCodeGenerator {

    public static Long generate(LocalDateTime dateTime) {
        return (dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
