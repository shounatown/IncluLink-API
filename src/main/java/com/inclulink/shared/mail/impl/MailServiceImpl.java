package com.inclulink.shared.mail.impl;

import com.inclulink.shared.mail.MailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${app.mail.from-name}")
    private String nameFrom;

    @Value("${spring.mail.username}")
    private String cc;

    @Override
    public void sendSimpleEmail(String to, String subject, String text) {
        send(to, subject, text, false);
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        send(to, subject, htmlContent, true);
    }


    private void send(String to, String subject, String content, boolean isHtml) {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setFrom(from, nameFrom);
            helper.setSubject(subject);

            helper.setText(content, isHtml);
            helper.setTo(to);

            helper.setCc(cc);

            mailSender.send(mensaje);
            log.info("Correo enviado con éxito a: {} [Modo HTML: {}]", to, isHtml);

        } catch (Exception ex) {
            log.error("Error al intentar enviar correo a {}: {}", to, ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error en el módulo de mensajería: " + ex.getMessage());
        }
    }
}