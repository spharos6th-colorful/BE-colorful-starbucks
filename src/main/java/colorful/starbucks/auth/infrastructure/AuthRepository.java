package colorful.starbucks.auth.infrastructure;

import colorful.starbucks.member.domain.Member;
import colorful.starbucks.auth.domain.SignType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberUuid(String uuid);

    Optional<Member> findByEmail(String email);

    Optional<Member> findBySignTypeAndSocialId(SignType signInType, String socialId);

}
