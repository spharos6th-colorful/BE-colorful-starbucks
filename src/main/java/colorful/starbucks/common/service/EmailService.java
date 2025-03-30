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
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendTempPassword(String toEmail, String tempPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("🔐 임시 비밀번호 안내");
            helper.setText(
                    "<h3>임시 비밀번호: " + tempPassword + "</h3>" +
                            "<p>로그인 후 반드시 비밀번호를 변경해주세요.</p>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("메일 전송 실패", e);
        }
    }
}

