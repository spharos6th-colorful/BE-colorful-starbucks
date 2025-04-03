package colorful.starbucks.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    /**
     * 2xx: 요청 성공
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    /**
     * 4xx
     */
    WRONG_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 401, "다시 로그인 해주세요"),
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 402, "로그인을 먼저 진행해주세요"),
    NO_ACCESS_AUTHORITY(HttpStatus.FORBIDDEN, false, 403, "접근 권한이 없습니다"),
    DISABLED_USER(HttpStatus.FORBIDDEN, false, 404, "비활성화된 계정입니다. 계정을 복구하시겠습니까?"),
    FAILED_TO_RESTORE(HttpStatus.INTERNAL_SERVER_ERROR, false, 405, "계정 복구에 실패했습니다. 관리자에게 문의해주세요."),
    NO_EXIST_OAUTH(HttpStatus.NOT_FOUND, false, 406, "소셜 로그인 정보가 존재하지 않습니다."),

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "리소스가 존재하지 않습니다."),

    REQUEST_CONFLICT(HttpStatus.CONFLICT, false, 409, "POST 요청에 실패했습니다."),
    SAME_NICKNAME(HttpStatus.CONFLICT, false, 409, "현재 사용중인 닉네임입니다."),
    INVALID_EMAIL_ADDRESS(HttpStatus.BAD_REQUEST, false, 400, "이메일을 다시 확인해주세요."),
    NO_EXIST_TERMS(HttpStatus.NOT_FOUND, false, 404, "존재하지 않는 약관입니다."),
    KAKAO_TOKEN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "카카오 토큰 발급에 실패했습니다."),
    KAKAO_USER_INFO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "카카오 사용자 정보 조회 실패"),
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "이메일 발송에 실패했습니다."),
    PASSWORD_RESET_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "비밀번호 초기화 중 오류 발생"),
    TERMS_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "약관 동의 저장에 실패했습니다."),
    INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, false, 400, "잘못된 인증번호입니다."),

    /**
     * 9xx: 기타 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "Internal server error"),
    SSE_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, false, 901, "알림 전송에 실패하였습니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 902, "Database error"),
    /**
     * 2000: users service error
     */
    // token
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, false, 2001, "토큰이 유효하지 않습니다."),

    // Users
    DUPLICATED_USER(HttpStatus.CONFLICT, false, 2101, "이미 가입된 멤버입니다."),
    FAILED_TO_LOGIN(HttpStatus.UNAUTHORIZED, false, 2102, "아이디 또는 패스워드를 다시 확인하세요."),
    DUPLICATED_SOCIAL_USER(HttpStatus.CONFLICT, false, 2103, "이미 소셜 연동된 계정입니다."),
    DUPLICATED_SOCIAL_PROVIDER_USER(HttpStatus.CONFLICT, false, 2104, "계정에 동일한 플랫폼이 이미 연동되어있습니다"),
    NO_EXIST_USER(HttpStatus.NOT_FOUND, false, 2105, "존재하지 않는 멤버 정보입니다."),
    PASSWORD_SAME_FAILED(HttpStatus.BAD_REQUEST, false, 2106, "현재 사용중인 비밀번호 입니다."),
    PASSWORD_CONTAIN_NUM_FAILED(HttpStatus.BAD_REQUEST, false, 2107, "휴대폰 번호를 포함한 비밀번호 입니다."),
    PASSWORD_MATCH_FAILED(HttpStatus.BAD_REQUEST, false, 2108, "패스워드를 다시 확인해주세요."),
    NO_SUPPORTED_PROVIDER(HttpStatus.BAD_REQUEST, false, 2109, "지원하지 않는 플랫폼입니다"),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, false, 2110, "이미 사용중인 닉네임입니다."),





    /**
     * 3000: product service error
     */

    // Product
    NO_EXIST_PRODUCT(HttpStatus.NOT_FOUND, false, 3001, "존재하지 않는 상품입니다"),
    NO_EXIST_OPTION(HttpStatus.NOT_FOUND, false, 3002, "존재하지 않는 옵션입니다"),
    NO_EXIST_CATEGORY(HttpStatus.NOT_FOUND, false, 3003, "존재하지 않는 카테고리입니다"),

    DUPLICATED_PRODUCT(HttpStatus.CONFLICT, false, 3004, "이미 등록된 상품입니다"),
    DUPLICATED_OPTION(HttpStatus.CONFLICT, false, 3005, "이미 등록된 옵션입니다"),
    DUPLICATED_CATEGORY(HttpStatus.CONFLICT, false, 3006, "이미 등록된 카테고리입니다"),

    NO_EXIST_OPTIONS_IN_PRODUCT(HttpStatus.NOT_FOUND, false, 3007, "해당 상품에 옵션이 존재하지 않습니다"),

    // Cart
    ALREADY_DELETED_CART_ITEM(HttpStatus.CONFLICT, false, 3008,"이미 삭제된 장바구니 상품입니다." ),
    DUPLICATED_DELIVERY(HttpStatus.CONFLICT, false, 3009, "이미 등록된 배송지입니다");


    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
