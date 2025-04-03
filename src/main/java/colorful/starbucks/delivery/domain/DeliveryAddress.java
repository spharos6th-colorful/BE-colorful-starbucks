package colorful.starbucks.delivery.domain;

import colorful.starbucks.common.entity.BaseEntity;
import colorful.starbucks.delivery.dto.request.DeliveryAddressEditRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_address_id")
    private Long id;


    @Column(nullable = false)
    @Comment("주소지 별칭")
    private String addressNickname;

    @Column(nullable = false)
    @Comment("수령인")
    private String receiverName;

    @Column(nullable = true)
    @Comment("기본 배송지 여부")
    private boolean isDefaultAddress;

    @Column(nullable = false)
    @Comment("우편 번호")
    private String zoneCode;

    @Column(nullable = false)
    @Comment("메인 주소")
    private String address;

    @Column(nullable = false)
    @Comment("상세 주소")
    private String detailAddress;

    @Column(nullable = false)
    @Comment("전화 번호")
    private String phoneNumber;

    @Column(nullable = false)
    @Comment("회원 uuid")
    private String memberUuid;

    @Column(nullable = false)
    @Comment("회원 주소 uuid")
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

    public void editAddress(DeliveryAddressEditRequestDto deliveryAddressEditRequestDto) {
        this.addressNickname = deliveryAddressEditRequestDto.getAddressNickname();
        this.receiverName = deliveryAddressEditRequestDto.getReceiverName();
        this.isDefaultAddress = deliveryAddressEditRequestDto.isDefaultAddress();
        this.zoneCode = deliveryAddressEditRequestDto.getZoneCode();
        this.address = deliveryAddressEditRequestDto.getAddress();
        this.detailAddress = deliveryAddressEditRequestDto.getDetailAddress();
        this.phoneNumber = deliveryAddressEditRequestDto.getPhoneNumber();
    }

}
