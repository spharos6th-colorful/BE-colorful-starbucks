package colorful.starbucks;

import colorful.starbucks.order.application.OrderService;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.request.OrderDetailCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderCreateResponseDto;
import colorful.starbucks.product.application.ProductDetailService;
import colorful.starbucks.product.dto.request.ProductDetailCodeAndQuantityRequestDto;
import colorful.starbucks.product.dto.response.ProductDetailCodeAndQuantityResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class StarbucksColorfulApplicationTests {

}
