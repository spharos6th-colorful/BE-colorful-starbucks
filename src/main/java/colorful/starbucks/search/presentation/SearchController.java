package colorful.starbucks.search.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.dto.request.CategoryCountRequestVo;
import colorful.starbucks.search.application.ProductSearchService;
import colorful.starbucks.search.dto.request.CategoryCountRequestDto;
import colorful.starbucks.search.dto.request.ElasticsearchRequestDto;
import colorful.starbucks.search.dto.response.CategoryCountResponseDto;
import colorful.starbucks.search.dto.response.ElasticsearchResponseDto;
import colorful.starbucks.search.vo.request.ElasticsearchRequestVo;
import colorful.starbucks.search.vo.response.AutoSearchListResponseVo;
import colorful.starbucks.search.vo.response.ElasticsearchResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/es")
@RequiredArgsConstructor
public class SearchController {

    private final ProductSearchService productSearchService;

    @Operation(
            summary = "자동완성 검색어 찾기 API",
            description = "자동완성 검색어 찾기를 위한 API 입니다.",
            tags = {"PRODUCT-SEARCH-SERVICE"}
    )
    @GetMapping("/auto-complete")
    public ApiResponse<AutoSearchListResponseVo> autoCompleteProductSearch(@RequestParam String keyword) throws IOException {

        return ApiResponse.ok("자동완성 찾기가 완료 되었습니다.",
                productSearchService.getAutoSearchList(keyword).toVo());
    }

    @Operation(
            summary = "엘라스틱 서치 검색 API",
            description = "엘라스틱 서치를 활용한 검색 API 입니다. 검색 키워드와 카테고리명으로 해당 상품 리스트 조회합니다. " +
                    "무한스크롤로 구현되어 있습니다. size를 결정하고 이전 목록 조회는 page 값을 할당하고 cursor는 빼주세요, 다음 목록 조회는 cursor로 요청해주세요.",
            tags = {"PRODUCT-SEARCH-SERVICE"}
    )
    @GetMapping("/search")
    public ApiResponse<CursorPage<ElasticsearchResponseVo>> searchWithElasticsearch(@ModelAttribute ElasticsearchRequestVo elasticsearchRequestVo) throws IOException {

        return ApiResponse.ok("엘라스틱 서치를 활용한 검색이 완료 되었습니다.",
                productSearchService.search(ElasticsearchRequestDto.from(elasticsearchRequestVo))
                        .map(ElasticsearchResponseDto::toVo));
    }

    @Operation(
            summary = "카테고리별 상품 개수 조회 API",
            description = "카테고리별 상품 개수를 조회하는 API 입니다. " +
                    "검색한 키워드에 해당하는 상품들이 속한 카테고리와 그 카테고리에 속한 상품 개수를 조회합니다.",
            tags = {"PRODUCT-SEARCH-SERVICE"}
    )
    @GetMapping("/categories")
    public ApiResponse<List<CategoryCountResponseDto>> getCategoryAndCount(@ModelAttribute CategoryCountRequestVo categoryCountRequestVo) throws IOException {
        return ApiResponse.ok("카테고리별 상품 개수 조회가 완료 되었습니다.",
                productSearchService.getCategoryAndCount(CategoryCountRequestDto.from(categoryCountRequestVo))
        );
    }
}
