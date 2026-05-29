package com.inclulink.shared.mail;
import org.thymeleaf.context.Context;

public interface MailService {

    void enviarCorreoHtml(String to, String asunto, String message);
    void enviarCorreoTemplate(String para, String asunto, String rutaTemplate, Context contexto);
}