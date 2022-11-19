package uz.sitelabs.car.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class MailService {

    @Value("${site.url}")
    private String siteUrl;

    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    public MailService(SpringTemplateEngine templateEngine,
                       JavaMailSender mailSender) {
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
    }

    @Async
    public void sendResetPassword(String email, String token) {
        Context context = new Context();
        context.setVariable("token", token);
        context.setVariable("siteUrl", siteUrl);
        String content = templateEngine.process("reset-password", context);
        String subject = "Confirm your email";
        sendEmail(email, subject, content);
    }

    @Async
    public void sendVerifyMail(String email, String token){
        Context context = new Context();
        context.setVariable("token", token);
        context.setVariable("siteUrl", siteUrl);
        String content = templateEngine.process("verify-mail", context);
        String subject = "Confirm your email";
        sendEmail(email, subject, content);
    }

    @Async
    public void sendEmail(String to, String subject, String content) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setFrom("no-reply@blahblah.com");
            helper.setReplyTo("no-reply@blahblah.com");
            helper.setSubject(subject);
            helper.setText(content, true);
            log.info("Sending email to: {}", to);
            mailSender.send(mimeMessage);
            log.debug("Email has been sent: {}{}", to, subject);
        } catch (MessagingException e){
            log.error("failed to send email. Error message: {}", e.getMessage());
        }
    }
}
