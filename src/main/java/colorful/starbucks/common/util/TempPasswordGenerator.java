package colorful.starbucks.common.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TempPasswordGenerator {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIALS = "~!@#$%^&";
    private static final String ALL = LETTERS + NUMBERS + SPECIALS;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate(int length) {
        if(length < 8){
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상입니다.");
        }

        List<Character> passwordChars = new ArrayList<>();

        passwordChars.add(randomChar(LETTERS));
        passwordChars.add(randomChar(NUMBERS));
        passwordChars.add(randomChar(SPECIALS));

        for(int i = passwordChars.size(); i < length; i++){
            passwordChars.add(randomChar(ALL));
        }

        Collections.shuffle(passwordChars);
        return toString(passwordChars);
    }

    private static char randomChar(String source){
        return source.charAt(RANDOM.nextInt(source.length()));
    }

    private static String toString(List<Character> passwordChars){
        return passwordChars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
