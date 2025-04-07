package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.vo.ProductSearchListFilterVo;
import colorful.starbucks.admin.vo.response.ProductSearchCursorResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class ProductSearchController {

    @GetMapping
    public ApiResponse<CursorPage<ProductSearchCursorResponseVo>> searchProducts(@ModelAttribute ProductSearchListFilterVo productSearchListFilterVo) {
        return null;
    }
}
