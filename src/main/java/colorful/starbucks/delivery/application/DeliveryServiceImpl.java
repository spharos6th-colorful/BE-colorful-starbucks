package colorful.starbucks.delivery.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.infrastructure.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    @Override
    public void addAddress(String memberUuid, DeliveryAddRequestDto deliveryAddRequestDto) {
        String memberAddressUuid = UUID.randomUUID().toString();

        if(deliveryRepository.existsByMemberUuidAndZoneCodeAndAddressAndDetailAddress(memberUuid,
                deliveryAddRequestDto.getZoneCode(),
                deliveryAddRequestDto.getAddress(),
                deliveryAddRequestDto.getDetailAddress())){
            throw new BaseException(ResponseStatus.DUPLICATED_DELIVERY, "이미 등록된 배송지 입니다.");
        }

        Optional<DeliveryAddress> deliveryAddress = deliveryRepository.findAllByMemberUuid((memberUuid));

        deliveryAddress.ifPresentOrElse(deliveryEntity->{deliveryRepository.save(deliveryAddRequestDto.toEntity(memberUuid,false, memberAddressUuid));
        },()-> deliveryRepository.save(deliveryAddRequestDto.toEntity(memberUuid,true, memberAddressUuid)));

    }

    @Transactional
    @Override
    public void deleteAddress(String memberUuid, String memberAddressUuid) {
        deliveryRepository.deleteByMemberUuidAndMemberAddressUuid(memberUuid, memberAddressUuid);
    }


}
