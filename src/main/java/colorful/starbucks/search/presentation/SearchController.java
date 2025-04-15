package colorful.starbucks.search.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.search.application.ProductSearchService;
import colorful.starbucks.search.domain.ProductDocument;
import colorful.starbucks.search.vo.response.AutoSearchListResponseVo;
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
    public List<ProductDocument> searchWithElasticsearch(@RequestParam String keyword) throws IOException {
        return productSearchService.search(keyword);
    }
}
