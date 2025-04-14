package colorful.starbucks.search.presentation;

import colorful.starbucks.search.application.ProductSearchSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/es/sync")
@RequiredArgsConstructor
public class DataSyncController {

    private final ProductSearchSyncService productSearchSyncService;

    @PostMapping
    public String syncProductSearch(){
        productSearchSyncService.syncAllToElasticsearch();
        return "동기화 완료!";
    }
}
