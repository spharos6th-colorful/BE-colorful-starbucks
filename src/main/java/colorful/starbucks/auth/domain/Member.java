package colorful.starbucks.auth.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String memberUuid;

    @Column(nullable = false, length = 50)
    private String memberName;

    @Column(nullable = false, length = 50, unique = true)
    private String Email;

    @Column(nullable = false, length = 20, unique = true)
    private String password;

    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;

    @Column(nullable = false, length = 20, unique = true)
    private String nickName;

    private String memberLevel;

    @Column(nullable = false, length = 20)
    private String memberBirth;

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
                  String memberLevel,
                  String memberBirth,
                   Gender gender) {

        this.id = id;
        this.memberUuid = memberUuid;
        this.memberName = memberName;
        this.Email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.memberLevel = memberLevel;
        this.memberBirth = memberBirth;
        this.gender = gender;
    }
}
