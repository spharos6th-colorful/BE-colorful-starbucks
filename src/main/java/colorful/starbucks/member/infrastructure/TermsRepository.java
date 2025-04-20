package colorful.starbucks.member.infrastructure;

import colorful.starbucks.member.domain.Terms;
import colorful.starbucks.member.domain.TermsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsRepository extends JpaRepository<Terms, Long> {

    List<Terms> findByTermsCategory(TermsCategory termsCategory);

}
