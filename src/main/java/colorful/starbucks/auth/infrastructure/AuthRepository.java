package colorful.starbucks.auth.infrastructure;

import colorful.starbucks.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberUuid(String uuid);

    boolean existsByEmail(String email);

    Member findByEmail(String email);
}
