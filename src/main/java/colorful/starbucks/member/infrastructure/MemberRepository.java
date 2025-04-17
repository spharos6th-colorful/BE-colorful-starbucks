package colorful.starbucks.member.infrastructure;

import colorful.starbucks.member.domain.Member;
import colorful.starbucks.member.domain.MemberLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndMemberNameAndPhoneNumber(String email, String memberName, String phoneNumber);

    Optional<Member> findByMemberNameAndPhoneNumber(String memberName, String phoneNumber);

    boolean existsByEmail(String email);

    Member findByMemberUuid(String memberUuid);

    Optional<Member> findOptionalByMemberUuid(String memberUuid);

    @Modifying
    @Query("UPDATE Member m SET m.memberLevel = :memberLevel WHERE m.memberUuid = :memberUuid")
    void updateMemberLevel(@Param("memberUuid") String memberUuid,
                           @Param("memberLevel") MemberLevel memberLevel);

}
