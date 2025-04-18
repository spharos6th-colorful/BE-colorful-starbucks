package colorful.starbucks.order.dto;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.vo.response.OrderCreateResponseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PreOrderDto {

    private Long orderCode;
    private Integer totalAmount;

    @Builder
    private PreOrderDto(Long orderCode, Integer totalAmount) {
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
    }


    public static PreOrderDto from(OrderCreateRequestDto dto, Long orderCode) {
        return PreOrderDto.builder()
                .orderCode(orderCode)
                .totalAmount(dto.getTotalAmount())
                .build();
    }

    public static PreOrderDto fromJson(String json, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, PreOrderDto.class);
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.REDIS_DESERIALIZE_FAIL);
        }
    }

    public String toJson(ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.REDIS_SERIALIZE_FAIL);
        }
    }

    public OrderCreateResponseVo toVo() {
        return OrderCreateResponseVo.builder()
                .orderCode(orderCode)
                .totalAmount(totalAmount)
                .build();
    }
}
