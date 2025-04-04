package colorful.starbucks.event.generator;

import java.util.UUID;

public class EventUuidGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
