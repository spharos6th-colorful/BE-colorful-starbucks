package colorful.starbucks.search.presentation;

import colorful.starbucks.search.application.ProductSearchService;
import colorful.starbucks.search.domain.ProductDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/es/sync")
@RequiredArgsConstructor
public class SearchController {

    private final ProductSearchService productSearchService;

    @PostMapping
    public String syncProductSearch(){
        productSearchService.syncAllToElasticsearch();
        return "동기화 완료!";
    }

    @GetMapping
    public List<ProductDocument> searchWithElasticsearch(@RequestParam String keyword) throws IOException {
        return productSearchService.search(keyword);
    }
}
