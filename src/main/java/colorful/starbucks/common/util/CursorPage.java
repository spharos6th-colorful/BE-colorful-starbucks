package colorful.starbucks.common.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
}
