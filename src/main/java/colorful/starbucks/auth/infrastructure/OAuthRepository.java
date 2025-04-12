package colorful.starbucks.auth.infrastructure;

import colorful.starbucks.auth.domain.OAuth;
import colorful.starbucks.auth.domain.SignType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {

    Optional<OAuth> findBySignTypeAndProviderId(SignType signType, String providerId);

    Optional<OAuth> findByMemberUuid(String memberUuid);
}
