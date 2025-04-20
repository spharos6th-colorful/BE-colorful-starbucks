package colorful.starbucks.search.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.search.application.ProductSearchService;
import colorful.starbucks.search.dto.request.ElasticsearchRequestDto;
import colorful.starbucks.search.dto.response.ElasticsearchResponseDto;
import colorful.starbucks.search.vo.request.ElasticsearchRequestVo;
import colorful.starbucks.search.vo.response.AutoSearchListResponseVo;
import colorful.starbucks.search.vo.response.ElasticsearchResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/es")
@RequiredArgsConstructor
public class SearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/auto-complete")
    public ApiResponse<AutoSearchListResponseVo> autoCompleteProductSearch(@RequestParam String keyword) throws IOException{

        return ApiResponse.ok("자동완성 찾기가 완료 되었습니다.",
                productSearchService.getAutoSearchList(keyword).toVo());
    }

    @GetMapping
    public ApiResponse<CursorPage<ElasticsearchResponseVo>> searchWithElasticsearch(@ModelAttribute ElasticsearchRequestVo elasticsearchRequestVo) throws IOException {

        return ApiResponse.ok("엘라스틱 서치를 활용한 검색이 완료 되었습니다.",
                productSearchService.search(ElasticsearchRequestDto.from(elasticsearchRequestVo))
                        .map(ElasticsearchResponseDto::toVo));
    }
}
