package colorful.starbucks.search.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.search.dto.request.ElasticsearchRequestDto;
import colorful.starbucks.search.dto.response.AutoSearchListResponseDto;
import colorful.starbucks.search.dto.response.ElasticsearchResponseDto;

import java.io.IOException;

public interface ProductSearchService {

    void syncAllToElasticsearch();
    AutoSearchListResponseDto getAutoSearchList(String keyword) throws IOException;
    CursorPage<ElasticsearchResponseDto> search(ElasticsearchRequestDto elasticsearchRequestDto) throws IOException;
}
