package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.InterestProductCreateRequestDto;
import colorful.starbucks.product.dto.response.InterestProductCreateResponseDto;
import colorful.starbucks.product.infrastructure.InterestProductRepository;
import lombok.RequiredArgsConstructor;
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
            throw new RuntimeException("관심 상품 삭제에 실패했습니다.");
        }

        interestProductRepository.deleteByMemberUuidAndProductCode(memberUuid, productCode);
    }
}
