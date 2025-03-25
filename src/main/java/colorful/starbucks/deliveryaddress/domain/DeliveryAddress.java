package colorful.starbucks.deliveryaddress.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_address_id")
    private Long id;

    private String addressNickname;

    private String receiverName;

    private boolean isDefaultAddress;

    private String postNumber;

    private String mainAddress;

    private String subAddress;

    private String phoneNumber;

    private String memberUuid;

    private String memberAddressUuid;

    @Builder
    private DeliveryAddress(Long id,
                            String addressNickname,
                            String receiverName,
                            boolean isDefaultAddress,
                            String postNumber,
                            String mainAddress,
                            String subAddress,
                            String phoneNumber,
                            String memberUuid,
                            String memberAddressUuid) {
        this.id = id;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.isDefaultAddress = isDefaultAddress;
        this.postNumber = postNumber;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
        this.phoneNumber = phoneNumber;
        this.memberUuid = memberUuid;
        this.memberAddressUuid = memberAddressUuid;
    }
}
