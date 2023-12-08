package com.example.service;

import com.example.entity.EmailEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.repository.EmailRepository;
import com.example.repository.ProfileRepository;
import io.jsonwebtoken.JwtException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${spring.mail.username}")
    private String fromAccount;
    private final ProfileService profileService;
    private final ProfileRepository profileRepository;
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;
    public String verification(String jwt) {
        Integer id;
        try {
            id = JwtService.decodeForEmailVerification(jwt);
        } catch (JwtException e) {
            return "Verification failed";
        }

        ProfileEntity exists = profileService.get(id);
        if (!exists.getStatus().equals(ProfileStatus.BLOCK)) {
            return "Verification failed";
        }
        exists.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(exists);

        return "Verification success";
    }
    public void sendVerificationEmail(ProfileEntity entity) {
        StringBuilder builder = new StringBuilder();
        String encode = JwtService.encode(entity.getId());
        builder.append("<h1 style=\"text-align: center\">Complete Registration</h1>");
        String link = String.format("<a href=\"http://localhost:8081/auth/verification/email/%s\"> Click there</a>",encode );
        builder.append(link);
        String title = "Activate Your Registration";
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmail(entity.getEmail());
        emailEntity.setMessage(encode);
        emailEntity.setCreatedDate(LocalDateTime.now());
        emailRepository.save(emailEntity);
        sendEmailMime(entity.getEmail(), title, builder.toString());
    }

  public   void sendEmailMime(String toAccount, String subject, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setFrom(fromAccount);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(toAccount);
            helper.setSubject(subject);
            helper.setText(content);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);

            EmailEntity emailHistory = new EmailEntity();
            emailHistory.setEmail(toAccount);
            emailHistory.setMessage(content);
            emailHistory.setCreatedDate(LocalDateTime.now());

            emailRepository.save(emailHistory);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
