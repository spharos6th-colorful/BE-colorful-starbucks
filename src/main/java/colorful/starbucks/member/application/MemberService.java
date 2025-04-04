package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.MemberMyPageEditRequestDto;
import colorful.starbucks.member.dto.response.MemberMyPageResponseDto;

public interface MemberService {

    MemberMyPageResponseDto getMyPage(String memberUuid);
    void editMyPage(MemberMyPageEditRequestDto memberMyPageEditRequestDto);

}
