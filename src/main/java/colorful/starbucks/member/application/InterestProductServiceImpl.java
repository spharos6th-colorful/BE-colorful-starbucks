package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.InterestProductDto;
import colorful.starbucks.member.dto.request.InterestProductAddRequestDto;
import colorful.starbucks.member.dto.request.InterestProductRemoveDto;
import colorful.starbucks.member.infrastructure.InterestProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterestProductServiceImpl implements InterestProductService {

    private final InterestProductRepository interestProductRepository;

    @Transactional
    @Override
    public void addInterestProduct(InterestProductAddRequestDto interestProductAddRequestDto) {
        interestProductRepository.save(interestProductAddRequestDto.toEntity());
    }

    @Override
    public Page<InterestProductDto> getInterestProductList(String memberUuid,
                                                           Pageable pageable) {

        return interestProductRepository.findAllByMemberUuidAndIsDeletedIsFalse(memberUuid, pageable)
                .map(InterestProductDto::from);
    }

    @Transactional
    @Override
    public void removeInterestProduct(InterestProductRemoveDto interestProductRemoveDto) {
        interestProductRepository.deleteByMemberUuidAndProductCode(
                interestProductRemoveDto.getMemberUuid(),
                interestProductRemoveDto.getProductCode()
        );
    }
}
