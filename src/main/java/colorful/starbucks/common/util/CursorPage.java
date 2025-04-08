package colorful.starbucks.common.util;

import colorful.starbucks.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CursorPage<T> {
    
    private List<T> content;
    private boolean hasNext;
    private Long nextCursor;

    @Builder
    private CursorPage(List<T> content, boolean hasNext, Long nextCursor) {
        this.content = content;
        this.hasNext = hasNext;
        this.nextCursor = nextCursor;
    }

    public <U> CursorPage<U> map(Function<? super T, ? extends U> mapper) {
        List<U> mappedContent = this.content.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return CursorPage.<U>builder()
                .content(mappedContent)
                .hasNext(this.hasNext)
                .nextCursor(this.nextCursor)
                .build();
    }
}
