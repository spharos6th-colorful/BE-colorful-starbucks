package colorful.starbucks.auth.infrastructure;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    Optional<Member> findByMemberUuid(String Uuid);

    MemberSignInResponseDto findByEmail(String email);
}
