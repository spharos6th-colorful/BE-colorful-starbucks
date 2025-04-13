package colorful.starbucks.payments.domain;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentsStatus {
    PENDING("결제전"),
    APPROVED("결제완료"),
    CANCELLED("주문취소"),
    FAILED("결제실패");

    private final String description;

    PaymentsStatus(String description) {
        this.description = description;
    }

    public static PaymentsStatus fromDescription(String description) {
       return Arrays.stream(values())
                .filter(paymentsStatus -> paymentsStatus.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new BaseException(ResponseStatus.INVALID_PAYMENTS_STATUS,"존재하지 않는 결제 상태입니다: " + description));
    }
}
