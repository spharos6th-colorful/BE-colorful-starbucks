package colorful.starbucks.search.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.search.application.ProductSearchService;
import colorful.starbucks.search.domain.ProductDocument;
import colorful.starbucks.search.dto.request.ElasticsearchRequestDto;
import colorful.starbucks.search.dto.response.ElasticsearchResponseDto;
import colorful.starbucks.search.vo.request.ElasticsearchRequestVo;
import colorful.starbucks.search.vo.response.AutoSearchListResponseVo;
import colorful.starbucks.search.vo.response.ElasticsearchResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/es")
@RequiredArgsConstructor
public class SearchController {

    private final ProductSearchService productSearchService;

    @PostMapping("/sync")
    public String syncProductSearch(){
        productSearchService.syncAllToElasticsearch();
        return "동기화 완료!";
    }

    //자동 완성
    @GetMapping("/autocomplete")
    public ApiResponse<AutoSearchListResponseVo> autoCompleteProductSearch(@RequestParam String keyword) throws IOException{

        return ApiResponse.ok("자동완성 찾기가 완료 되었습니다.",
                productSearchService.getAutoSearchList(keyword).toVo());
    }



    //검색 결과
    @GetMapping
    public ApiResponse<CursorPage<ElasticsearchResponseVo>> searchWithElasticsearch(@ModelAttribute ElasticsearchRequestVo elasticsearchRequestVo) throws IOException {


        return ApiResponse.ok("엘라스틱 서치를 활용한 검색이 완료 되었습니다.",
                productSearchService.search2(ElasticsearchRequestDto.from(elasticsearchRequestVo))
                        .map(ElasticsearchResponseDto::toVo));
    }
}
