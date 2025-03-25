package colorful.starbucks.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String memberName;

    private String Email;

    private String password;

    private String phoneNumber;

    private String nickName;

    private String memberUuid;

    private String memberLevel;

    private String memberBirth;

    @Builder
    private Member(Long id,
                  String memberName,
                  String email,
                  String password,
                  String phoneNumber,
                  String nickName,
                  String memberUuid,
                  String memberLevel,
                  String memberBirth) {

        this.id = id;
        this.memberName = memberName;
        Email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.memberUuid = memberUuid;
        this.memberLevel = memberLevel;
        this.memberBirth = memberBirth;
    }
}
