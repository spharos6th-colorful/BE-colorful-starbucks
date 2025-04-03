package colorful.starbucks.delivery.infrastructure;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<DeliveryAddress, Long> {

    Boolean existsByMemberUuidAndZoneCodeAndAddressAndDetailAddress(String memberUuid, String zoneCode, String address, String detailAddress);

    Optional<DeliveryAddress> findByMemberUuid(String memberUuid);

    void deleteByMemberUuidAndMemberAddressUuid(String memberUuid, String memberAddressUuid);

    Optional<DeliveryAddress> findByMemberUuidAndMemberAddressUuid(String memberUuid, String memberAddressUuid);

    Optional<DeliveryAddress> findByMemberUuidAndIsDefaultAddress(String memberUuid, boolean isDefaultAddress);

    List<DeliveryAddress> findAllByMemberUuid(String memberUuid);

    DeliveryAddress findByMemberAddressUuid(String memberAddressUuid);
}
