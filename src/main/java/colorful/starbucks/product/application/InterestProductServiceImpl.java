package colorful.starbucks.product.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.product.dto.request.InterestProductCreateRequestDto;
import colorful.starbucks.product.dto.response.InterestProductCreateResponseDto;
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
    public InterestProductCreateResponseDto createInterestProduct(
            InterestProductCreateRequestDto interestProductCreateRequestDto
    ) {

        return InterestProductCreateResponseDto.from(
                interestProductRepository.save(interestProductCreateRequestDto.toEntity())
        );
    }

    @Transactional
    @Override
    public void removeInterestProduct(String memberUuid, String productCode) {

        if (!interestProductRepository.existsInterestProductByMemberUuidAndProductCode(memberUuid, productCode)) {
            throw new BaseException(ResponseStatus.RESOURCE_NOT_FOUND);
        }

        interestProductRepository.deleteByMemberUuidAndProductCode(memberUuid, productCode);
    }

    @Override
    public InterestProductListResponseDto getInterestProductList(String memberUuid, Pageable pageable) {

        return InterestProductListResponseDto.from(
                interestProductRepository.findAllByMemberUuid(memberUuid, pageable)
        );
    }
}
