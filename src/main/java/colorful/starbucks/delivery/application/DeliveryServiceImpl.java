package colorful.starbucks.delivery.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.dto.request.*;
import colorful.starbucks.delivery.dto.response.DeliveryAddressResponseDto;
import colorful.starbucks.delivery.dto.response.DeliveryAddressesResponseDto;
import colorful.starbucks.delivery.dto.response.DeliveryDefaultAddressResponseDto;
import colorful.starbucks.delivery.generator.MemberAddressUuidGenerator;
import colorful.starbucks.delivery.infrastructure.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    @Override
    public void addAddress(DeliveryAddRequestDto deliveryAddRequestDto) {
        Optional<DeliveryAddress> deliveryAddress =
                deliveryRepository.findByMemberUuid((deliveryAddRequestDto.getMemberUuid()));
        boolean isDefaultAddress = deliveryAddress.isPresent() ? false : true;
        deliveryRepository.save(deliveryAddRequestDto.toEntity(deliveryAddRequestDto.getMemberUuid(), isDefaultAddress, MemberAddressUuidGenerator.generate()));
    }

    @Transactional
    @Override
    public void deleteAddress(DeliveryDeleteRequestDto deliveryDeleteRequestDto) {
        deliveryRepository.deleteByMemberUuidAndMemberAddressUuid(deliveryDeleteRequestDto.getMemberUuid(),
                deliveryDeleteRequestDto.getMemberAddressUuid());
    }


    @Override
    public DeliveryAddressResponseDto getIndividualAddress(DeliveryAddressRequestDto deliveryAddressRequestDto) {
        DeliveryAddress deliveryAddress = deliveryRepository.findByMemberUuidAndMemberAddressUuid(
                deliveryAddressRequestDto.getMemberUuid(),
                deliveryAddressRequestDto.getMemberAddressUuid()
        ).orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        return DeliveryAddressResponseDto.from(deliveryAddress);
    }

    @Transactional
    @Override
    public void editAddress(DeliveryAddressEditRequestDto deliveryAddressEditRequestDto) {

        if (deliveryAddressEditRequestDto.isDefaultAddress()) {
            changeDefaultAddressToFalse(deliveryAddressEditRequestDto.getMemberUuid());
        }
        deliveryRepository.findByMemberAddressUuid(deliveryAddressEditRequestDto.getMemberAddressUuid())
                .editAddress(deliveryAddressEditRequestDto);
    }

    @Transactional
    @Override
    public void editDefaultAddress(DeliveryDefaultAddressRequestDto deliveryDefaultAddressRequestDto) {

        changeDefaultAddressToFalse(deliveryDefaultAddressRequestDto.getMemberUuid());

        DeliveryAddress deliveryAddress = deliveryRepository.findByMemberUuidAndMemberAddressUuid(deliveryDefaultAddressRequestDto.getMemberUuid(),
                deliveryDefaultAddressRequestDto.getMemberAddressUuid()).orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        deliveryAddress.updateIsDefaultAddress(true);
    }

    public void changeDefaultAddressToFalse(String memberUuid) {

        DeliveryAddress deliveryAddress = deliveryRepository.findByMemberUuidAndIsDefaultAddress(memberUuid, true)
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        deliveryAddress.updateIsDefaultAddress(false);

    }

    @Override
    public DeliveryDefaultAddressResponseDto getDefaultAddress(String memberUuid) {

        DeliveryAddress deliveryAddress = deliveryRepository.findByMemberUuidAndIsDefaultAddress(memberUuid, true)
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND, "기본 배송지가 존재하지 않습니다."));

        return DeliveryDefaultAddressResponseDto.from(deliveryAddress);
    }

    @Override
    public List<DeliveryAddressesResponseDto> getAddressList(String memberUuid) {

        return deliveryRepository.findAllByMemberUuid(memberUuid)
                .stream()
                .map(DeliveryAddressesResponseDto::from)
                .collect(Collectors.toList());
    }

}


