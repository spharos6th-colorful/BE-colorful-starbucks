package colorful.starbucks.product.presentation;

import colorful.starbucks.product.vo.request.ProductCreateRequestVo;
import colorful.starbucks.product.vo.response.ProductResponseVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @DisplayName("상품 데이터 삽입")
    @Test
    void insertProductData() throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "http://localhost:8080/api/v1/products";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        for (int i = 300000; i < 500_000; i++) {
            ProductCreateRequestVo request = ProductCreateRequestVo.builder()
                    .productName("product" + i)
                    .productTopCategoryName("topcategory" + i)
                    .productBottomCategoryName("bottomcategory" + i)
                    .description("description" + i)
                    .markable(false)
                    .price(1000 + i)
                    .build();

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("productCreateRequestVo", request);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<ProductResponseVo> response = restTemplate.postForEntity(url, requestEntity, ProductResponseVo.class);

//            assertEquals(201, response.getStatusCodeValue());
            // Add more assertions as needed
        }
    }
}