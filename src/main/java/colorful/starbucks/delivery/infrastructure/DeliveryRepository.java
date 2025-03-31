package colorful.starbucks.delivery.infrastructure;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<DeliveryAddress, Long> {


    Boolean existsByMemberUuidAndZoneCodeAndAddressAndDetailAddress(String memberUuid, String zoneCode, String address, String detailAddress);

    Optional<DeliveryAddress> findAllByMemberUuid(String memberUuid);

    @Modifying
    @Query("delete from DeliveryAddress d " +
            "where d.memberUuid = :memberUuid and d.memberAddressUuid = :memberAddressUuid")
    void deleteByMemberUuidAndMemberAddressUuid(String memberUuid, String memberAddressUuid);

}
