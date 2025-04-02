package colorful.starbucks.delivery.domain;

import colorful.starbucks.common.entity.BaseEntity;
import colorful.starbucks.delivery.dto.request.DeliveryAddressEditRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_address_id")
    private Long id;

    private String addressNickname;

    private String receiverName;

    private boolean isDefaultAddress;

    private String zoneCode;

    private String address;

    private String detailAddress;

    private String phoneNumber;

    private String memberUuid;

    private String memberAddressUuid;

    @Builder
    private DeliveryAddress(Long id,
                            String addressNickname,
                            String receiverName,
                            boolean isDefaultAddress,
                            String zoneCode,
                            String address,
                            String detailAddress,
                            String phoneNumber,
                            String memberUuid,
                            String memberAddressUuid) {
        this.id = id;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.isDefaultAddress = isDefaultAddress;
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.memberUuid = memberUuid;
        this.memberAddressUuid = memberAddressUuid;
    }

    public void updateIsDefaultAddress(boolean isDefaultAddress) {
        this.isDefaultAddress = isDefaultAddress;
    }
    public void editAddress(DeliveryAddressEditRequestDto deliveryAddressEditRequestDto) {
        this.address = deliveryAddressEditRequestDto.getAddress() == null ?this.address : deliveryAddressEditRequestDto.getAddress();
        this.zoneCode = deliveryAddressEditRequestDto.getZoneCode() == null ?this.zoneCode : deliveryAddressEditRequestDto.getZoneCode();
        this.detailAddress = deliveryAddressEditRequestDto.getDetailAddress() == null ?this.detailAddress : deliveryAddressEditRequestDto.getDetailAddress();
        this.phoneNumber = deliveryAddressEditRequestDto.getPhoneNumber() == null ?this.phoneNumber : deliveryAddressEditRequestDto.getPhoneNumber();
        this.addressNickname = deliveryAddressEditRequestDto.getAddressNickname() == null ?this.addressNickname : deliveryAddressEditRequestDto.getAddressNickname();
        this.receiverName = deliveryAddressEditRequestDto.getReceiverName() == null ?this.receiverName : deliveryAddressEditRequestDto.getReceiverName();
        this.isDefaultAddress = deliveryAddressEditRequestDto.isDefaultAddress();
    }

}
