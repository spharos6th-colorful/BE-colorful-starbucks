package colorful.starbucks.member.domain;

import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String memberUuid;

    @Comment("회원 이름")
    @Column(nullable = true, length = 50)
    private String memberName;

    @Comment("회원 이메일")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Comment("회원 비밀번호")
    @Column(nullable = true, length = 100, unique = true)
    private String password;

    @Comment("회원 휴대전화 번호")
    @Column(nullable = true, length = 20, unique = true)
    private String phoneNumber;

    @Comment("회원 닉네임")
    @Column(nullable = true, length = 20, unique = true)
    private String nickName;

    @Comment("회원 등급")
    @Enumerated(EnumType.STRING)
    private MemberLevel memberLevel;

    @Comment("회원 생년월일")
    @Column(nullable = true, length = 20)
    private String memberBirth;

    @Comment("회원 성별")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    private Member(Long id,
                   String memberUuid,
                   String memberName,
                   String email,
                   String password,
                   String phoneNumber,
                   String nickName,
                   MemberLevel memberLevel,
                   String memberBirth,
                   Gender gender) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.memberLevel = memberLevel;
        this.memberBirth = memberBirth;
        this.gender = gender;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
