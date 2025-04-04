package colorful.starbucks.member.infrastructure;

import colorful.starbucks.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findAllByMemberUuid(String memberUuid);
}
