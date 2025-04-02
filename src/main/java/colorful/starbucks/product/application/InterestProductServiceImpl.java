package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.InterestProductAddRequestDto;
import colorful.starbucks.product.dto.response.InterestProductListResponseDto;
import colorful.starbucks.product.infrastructure.InterestProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestProductServiceImpl implements InterestProductService {

    private final InterestProductRepository interestProductRepository;

    @Transactional
    @Override
    public void addInterestProduct(InterestProductAddRequestDto interestProductAddRequestDto) {
        interestProductRepository.save(interestProductAddRequestDto.toEntity());
    }

    @Transactional
    @Override
    public void removeInterestProduct(Long interestProductId, String memberUuid) {
        interestProductRepository.deleteByIdAndMemberUuid(interestProductId, memberUuid);
    }

    @Override
    public InterestProductListResponseDto getInterestProductList(String memberUuid,
                                                                 Pageable pageable) {
        return InterestProductListResponseDto.from(
                interestProductRepository.findAllByMemberUuidAndIsDeletedIsFalse(memberUuid, pageable)
        );
    }
}
