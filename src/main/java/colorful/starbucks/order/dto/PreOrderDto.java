package colorful.starbucks.order.dto;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PreOrderDto {

    private String memberUuid;
    private String couponUuid;
    private int totalAmount;
    private int discountAmount;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private Boolean isGifted;
    private String buyer;



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
}

