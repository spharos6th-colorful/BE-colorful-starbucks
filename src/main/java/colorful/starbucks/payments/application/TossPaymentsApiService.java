package colorful.starbucks.payments.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@RequiredArgsConstructor
@Transactional
public class TossPaymentsApiService {

    @Value("${toss.secret-key}")
    private String secretKey;
    @Value("${toss.confirm}")
    private String confirm;

    private final RestTemplate restTemplate;

    public String approvePayment(String paymentKey, String orderId, int amount) {

        String encodedSecretKey = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());



        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("Authorization", "Basic " + encodedSecretKey);

       JSONObject body = new JSONObject();

        body.put("paymentKey", paymentKey);
        body.put("orderId", orderId);
        body.put("amount", amount);

        HttpEntity<String> requestEntity = new HttpEntity<>(body.toString(), headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(confirm, requestEntity, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                throw new BaseException(ResponseStatus.PAYMENT_APPROVAL_FAILED, "결제 승인 실패");
            }

        } catch (Exception e) {
            throw new BaseException(ResponseStatus.PAYMENT_APPROVAL_FAILED, "토스 결제 승인 중 오류: " + e.getMessage());

        }
    }

    }



