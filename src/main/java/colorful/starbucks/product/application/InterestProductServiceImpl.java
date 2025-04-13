package colorful.starbucks.product.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.product.dto.InterestProductDto;
import colorful.starbucks.product.dto.request.InterestProductAddRequestDto;
import colorful.starbucks.product.infrastructure.InterestProductRepository;
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
    public void removeInterestProduct(Long interestProductId, String memberUuid) {
        interestProductRepository.findByIdAndMemberUuid(interestProductId, memberUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        interestProductRepository.deleteById(interestProductId);
    }
}
