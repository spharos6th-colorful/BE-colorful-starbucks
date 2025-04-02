package colorful.starbucks.product.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.product.domain.InterestProduct;
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
        interestProductRepository.findByIdAndMemberUuid(interestProductId, memberUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        interestProductRepository.deleteById(interestProductId);
    }

    @Override
    public InterestProductListResponseDto getInterestProductList(String memberUuid,
                                                                 Pageable pageable) {
        return InterestProductListResponseDto.from(
                interestProductRepository.findAllByMemberUuidAndIsDeletedIsFalse(memberUuid, pageable)
        );
    }
}
