package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.MemberEmailFindRequestDto;
import colorful.starbucks.auth.dto.request.MemberPasswordResetRequestDto;
import colorful.starbucks.auth.dto.response.MemberEmailFindResponseDto;
import colorful.starbucks.auth.dto.response.MemberPasswordResetResponseDto;

public interface AccountRecoveryService {

    MemberEmailFindResponseDto findEmail(MemberEmailFindRequestDto memberEmailFindRequestDto);

    MemberPasswordResetResponseDto findPassword(MemberPasswordResetRequestDto memberPasswordResetRequestDto);

    boolean isEmailDuplicated(String email);

}
