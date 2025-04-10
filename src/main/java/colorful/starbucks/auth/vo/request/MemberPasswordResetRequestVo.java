package colorful.starbucks.auth.vo.request;

import lombok.Getter;

@Getter
public class MemberPasswordResetRequestVo {

    private String memberName;
    private String email;
    private String phoneNumber;


}
