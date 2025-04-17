package colorful.starbucks.event.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.event.application.EventProductService;
import colorful.starbucks.event.dto.request.EventProductCodesRequestDto;
import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.response.EventProductCodesResponseDto;
import colorful.starbucks.event.vo.request.EventProductCodesRequestVo;
import colorful.starbucks.event.vo.request.EventProductCreateRequestVo;
import colorful.starbucks.event.vo.response.EventProductCodesResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventProductController {

    private final EventProductService eventProductService;

    @Operation(
            summary = "이벤트 상품 등록 API",
            description = "이벤트 상품을 등록하는 API 입니다.",
            tags = {"EVENT-SERVICE"}
    )
    @PostMapping("{eventUuid}/products")
    public ApiResponse<Void> createEventProduct(@PathVariable String eventUuid,
                                                @RequestBody List<EventProductCreateRequestVo> eventProductCreateRequestVos) {

        eventProductService.createEventProduct(eventProductCreateRequestVos.stream()
                .map(e -> EventProductCreateRequestDto.from(eventUuid, e)).toList());
        return ApiResponse.of(HttpStatus.CREATED,
                "이벤트 상품 등록을 완료했습니다.",
                null);
    }

    @Operation(
            summary = "이벤트 상품 코드 조회 API",
            description = "이벤트 상품 코드를 조회하는 API 입니다. 무한스크롤 기능이 적용되어 있습니다." +
                    "size를 결정하고 이전 목록 조회는 page 값을 할당하고 cursor는 빼주세요, 다음 목록 조회는 cursor로 요청해주세요.",
            tags = {"EVENT-SERVICE"}
    )
    @GetMapping("{eventUuid}/products")
    public ApiResponse<CursorPage<EventProductCodesResponseVo>> getEventProductCodes(@PathVariable String eventUuid,
                                                                                     @ModelAttribute EventProductCodesRequestVo eventProductCodesRequestVo) {
        ;
        return ApiResponse.ok(
                "이벤트 상품 조회를 완료했습니다.",
                eventProductService.getEventProductCodes(EventProductCodesRequestDto.from(eventUuid, eventProductCodesRequestVo))
                        .map(EventProductCodesResponseDto::toVo)
        );
    }

}
