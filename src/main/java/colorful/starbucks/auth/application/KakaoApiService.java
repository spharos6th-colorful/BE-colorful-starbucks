package colorful.starbucks.auth.application;


import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import colorful.starbucks.auth.dto.response.KakaoUserInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoApiService {

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    private final String tokenUri = "https://kauth.kakao.com/oauth/token";
    private final String userInfoUri = "https://kapi.kakao.com/v2/user/me";
    private final RestTemplate restTemplate;

    public String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // ✅ 주입된 restTemplate 사용
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUri, request, String.class);

        JSONObject json = new JSONObject(response.getBody());
        return json.getString("access_token");
    }

    public KakaoUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        // ✅ 주입된 restTemplate 사용
        ResponseEntity<String> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, String.class);
        JSONObject json = new JSONObject(response.getBody());

        String id = json.get("id").toString();
        String email = null;

        if (json.has("kakao_account")) {
            JSONObject account = json.getJSONObject("kakao_account");
            if (account.has("email")) {
                email = account.getString("email");
            }
        }

        return new KakaoUserInfo(id, email);
    }
}
