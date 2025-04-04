package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.response.MemberMyPageResponseDto;

public interface MemberService {

    MemberMyPageResponseDto getMyPage(String memberUuid);

}
