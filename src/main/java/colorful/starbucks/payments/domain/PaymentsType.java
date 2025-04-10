package colorful.starbucks.payments.domain;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import lombok.Getter;

@Getter
public enum PaymentsType {
    CARD("신용카드"),
    VIRTUAL_ACCOUNT("가상계좌"),
    ACCOUNT_TRANSFER("계좌이체"),
    MOBILE_PHONE("휴대폰소액결제"),
    GIFT_CERTIFICATE("상품권"),
    EASY_PAY("간편결제"),
    POINT("포인트");

    private final String description;

    PaymentsType(String description) {this.description = description;}

    public static PaymentsType fromDescription(String description) {
        for (PaymentsType type : values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new BaseException(ResponseStatus.INVALID_PAMENT_TYPE, "존재하지 않는 결제 수단입니다: " + description);
    }

}
