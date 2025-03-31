package colorful.starbucks.delivery.infrastructure;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<DeliveryAddress, Long> {


    Boolean existsByMemberUuidAndZoneCodeAndAddressAndDetailAddress(String memberUuid, String zoneCode, String address, String detailAddress);

    Optional<DeliveryAddress> findAllByMemberUuid(String memberUuid);

}
