package colorful.starbucks.common.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailSendService {

    private final JavaMailSender mailSender;

    public void sendTempPassword(String toEmail, String tempPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setFrom("jjj8219@gmail.com");
            helper.setSubject("🔑 임시 비밀번호 안내드립니다");

            helper.setText(
                    "<div style='font-family: Arial, sans-serif;'>" +
                            "<h2>🔐 임시 비밀번호: <strong>" + tempPassword + "</strong></h2>" +
                            "<p>보안을 위해 <b>로그인 후 반드시 비밀번호를 변경</b>해주세요.</p>" +
                            "<p>이 비밀번호는 1회용이며, 재사용이 불가능합니다.</p>" +
                            "<br><p style='color:gray;'>본 메일은 발신 전용입니다.</p>" +
                            "</div>",
                    true
            );
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("메일 전송 실패", e);
        }
    }


    public void sendEmailCode(String toEmail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setFrom("jjj8219@gmail.com");
            helper.setSubject("📬 이메일 인증번호 안내드립니다");

            helper.setText(
                    "<div style='font-family: Arial, sans-serif;'>" +
                            "<h2>🔐 인증번호: <strong>" + code + "</strong></h2>" +
                            "<p>아래 인증번호를 인증 페이지에 입력해주세요.</p>" +
                            "<p><b>유효시간은 3분</b>입니다. 시간 내에 입력하지 않으면 인증이 만료됩니다.</p>" +
                            "<br><p style='color:gray;'>본 메일은 발신 전용입니다.</p>" +
                            "</div>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("메일 전송 실패", e);
        }
    }
}