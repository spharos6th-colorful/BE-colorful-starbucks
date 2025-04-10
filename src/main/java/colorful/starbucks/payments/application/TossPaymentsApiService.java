package colorful.starbucks.payments.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    public String approvePayment(String paymentKey, String orderCode, Integer amount) {

        String encodedSecretKey = Base64.getEncoder().encodeToString((secretKey + ":" + orderCode).getBytes());

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("Authorization", "Basic " + encodedSecretKey);

       JSONObject body = new JsonObject();

        body.add("paymentKey", paymentKey);
        body.add("orderCode", orderCode);
        body.add("amount", amount);

        HttpEntity<MultiValueMap<String, Object>> requstEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(confirm,requstEntity, String.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new BaseException(ResponseStatus.PAYMENT_APPROVAL_FAILED,"결제 승인 실패");
        }

    }


}
