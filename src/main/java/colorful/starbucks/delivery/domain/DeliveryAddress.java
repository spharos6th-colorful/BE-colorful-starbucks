package colorful.starbucks.delivery.domain;

import colorful.starbucks.common.entity.BaseEntity;
import colorful.starbucks.delivery.dto.request.DeliveryAddressEditRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_address_id")
    private Long id;

    @Comment("주소지 별칭")
    @Column(nullable = false)
    private String addressNickname;

    @Comment("수령인")
    @Column(nullable = false)
    private String receiverName;

    @Comment("기본 배송지 여부")
    @Column(nullable = true)
    private boolean isDefaultAddress;

    @Comment("우편 번호")
    @Column(nullable = false)
    private String zoneCode;

    @Comment("메인 주소")
    @Column(nullable = false)
    private String address;

    @Comment("상세 주소")
    @Column(nullable = false)
    private String detailAddress;

    @Comment("전화 번호")
    @Column(nullable = false)
    private String phoneNumber;

    @Comment("회원 uuid")
    @Column(nullable = false)
    private String memberUuid;

    @Comment("회원 주소 uuid")
    @Column(nullable = false)
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

    public void updateIsDefaultAddress(boolean newAddressDefault) {
        this.isDefaultAddress = newAddressDefault;
    }
    public DeliveryAddress editAddress(DeliveryAddressEditRequestDto deliveryAddressEditRequestDto) {
        return DeliveryAddress.builder()
                .id(this.id)
                .isDefaultAddress(deliveryAddressEditRequestDto.getDefaultAddress())
                .memberUuid(deliveryAddressEditRequestDto.getMemberUuid())
                .memberAddressUuid(deliveryAddressEditRequestDto.getMemberAddressUuid())
                .addressNickname(deliveryAddressEditRequestDto.getAddressNickname())
                .receiverName(deliveryAddressEditRequestDto.getReceiverName())
                .zoneCode(deliveryAddressEditRequestDto.getZoneCode())
                .address(deliveryAddressEditRequestDto.getAddress())
                .detailAddress(deliveryAddressEditRequestDto.getDetailAddress())
                .phoneNumber(deliveryAddressEditRequestDto.getPhoneNumber())
                .build();
    }

}
