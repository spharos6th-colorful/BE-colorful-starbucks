package colorful.starbucks.auth.infrastructure;

import colorful.starbucks.auth.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {



    boolean existsByEmail(String email);

}
