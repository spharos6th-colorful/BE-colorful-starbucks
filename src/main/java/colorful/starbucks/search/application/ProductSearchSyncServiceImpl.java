package colorful.starbucks.search.application;

import colorful.starbucks.search.domain.ProductDocument;
import colorful.starbucks.search.dto.ProductSearchDto;
import colorful.starbucks.search.infrastructure.ProductDocumentRepository;
import colorful.starbucks.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchSyncServiceImpl implements ProductSearchSyncService {

    private final ProductRepository productRepository;
    private final ProductDocumentRepository productDocumentRepository;

    @Override
    public void syncAllToElasticsearch() {
        List<ProductSearchDto> searchDtoList = productRepository.findAllForSearch();

        List<ProductDocument> documents = searchDtoList.stream()
                .map(dto-> new ProductDocument(
                        dto.getProductCode(),
                        dto.getProductName(),
                        dto.getTopCategoryName(),
                        dto.getBottomCategoryName()
                ))
                .toList();
        productDocumentRepository.saveAll(documents);
    }
}
