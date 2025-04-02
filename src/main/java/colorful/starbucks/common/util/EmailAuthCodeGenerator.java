package colorful.starbucks.common.util;

import java.security.SecureRandom;

public class EmailAuthCodeGenerator {

    private static final String NUMBERS = "0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateCode(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("인증코드는 4자리입니다");
        }
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
        }
        return code.toString();
    }

}
