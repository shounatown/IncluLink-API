package com.inclulink.shared.mail.impl;

import com.inclulink.shared.mail.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void enviarCorreoHtml(String para, String asunto, String cuerpoHtml) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
            helper.setTo(para);
            helper.setSubject(asunto);
            helper.setText(cuerpoHtml, true);
            mailSender.send(mensaje);
            log.info("Correo HTML enviado con éxito a: {}", para);
        } catch (MessagingException e) {
            log.error("Error al enviar correo: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enviarCorreoTemplate(String para, String asunto, String rutaTemplate, Context contexto) {
        String cuerpoHtmlProcesado = templateEngine.process(rutaTemplate, contexto);
        this.enviarCorreoHtml(para, asunto, cuerpoHtmlProcesado);
    }
}