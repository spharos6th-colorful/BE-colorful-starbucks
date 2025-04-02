package colorful.starbucks.delivery.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryAddressEditRequestDto;
import colorful.starbucks.delivery.dto.response.DeliveryIndividualAddressResponseDto;
import colorful.starbucks.delivery.generator.MemberAddressUuidGenerator;
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
    public void addAddress(DeliveryAddRequestDto deliveryAddRequestDto) {

        if(deliveryRepository.existsByMemberUuidAndZoneCodeAndAddressAndDetailAddress(deliveryAddRequestDto.getMemberUuid(),
                deliveryAddRequestDto.getZoneCode(),
                deliveryAddRequestDto.getAddress(),
                deliveryAddRequestDto.getDetailAddress())){
            throw new BaseException(ResponseStatus.DUPLICATED_DELIVERY, "이미 등록된 배송지 입니다.");
        }

        Optional<DeliveryAddress> deliveryAddress =
                deliveryRepository.findAllByMemberUuid((deliveryAddRequestDto.getMemberUuid()));
        boolean isDefaultAddress = deliveryAddress.isPresent() ? false : true;
        deliveryRepository.save(deliveryAddRequestDto.toEntity(deliveryAddRequestDto.getMemberUuid(), isDefaultAddress, MemberAddressUuidGenerator.generate()));
    }

    @Transactional
    @Override
    public void deleteAddress(String memberUuid, String memberAddressUuid) {
        deliveryRepository.deleteByMemberUuidAndMemberAddressUuid(memberUuid, memberAddressUuid);
    }


    @Override
    public DeliveryIndividualAddressResponseDto getIndividualAddress(String memberUuid, String memberAddressUuid) {
        DeliveryAddress deliveryAddress = deliveryRepository.findByMemberUuidAndMemberAddressUuid(memberUuid, memberAddressUuid).orElseThrow(()-> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        return DeliveryIndividualAddressResponseDto.from(deliveryAddress);
    }

    @Transactional
    @Override
    public void editAddress(String memberUuid, String memberAddressUuid, DeliveryAddressEditRequestDto deliveryAddressEditRequestDto) {
        // 기본 배송지로 변경한 경우
        if(deliveryAddressEditRequestDto.isDefaultAddress()){
            DeliveryAddress deliveryAddress = deliveryRepository.findByMemberUuidAndIsDefaultAddress(memberUuid, true);
            deliveryAddress.updateIsDefaultAddress(false);
        }

        if(deliveryRepository.existsByMemberUuidAndZoneCodeAndAddressAndDetailAddress(memberUuid,
                deliveryAddressEditRequestDto.getZoneCode(),
                deliveryAddressEditRequestDto.getAddress(),
                deliveryAddressEditRequestDto.getDetailAddress())){
            throw new BaseException(ResponseStatus.DUPLICATED_DELIVERY, "이미 등록된 배송지 입니다.");
        }
        DeliveryAddress deliveryAddress = deliveryRepository.findByMemberUuidAndMemberAddressUuid(memberUuid, memberAddressUuid).orElseThrow(()-> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));


    }


}
