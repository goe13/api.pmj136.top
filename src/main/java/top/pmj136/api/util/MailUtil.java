package top.pmj136.api.util;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 彭明久
 * @since 2020-11-18
 */
@Component
public class MailUtil {
    @Resource
    private JavaMailSender javaMailSender1;

    @Resource
    private MailProperties mailProperties1;

    private static MailProperties mailProperties;
    private static JavaMailSender javaMailSender;

    @PostConstruct
    public void init() {
        mailProperties = mailProperties1;
        javaMailSender = javaMailSender1;
    }

    /*发送邮件*/
    public static void send(String source, String text, String target) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject(source);
        smm.setText(text);
        smm.setFrom(mailProperties.getUsername());
        smm.setTo(target);
        javaMailSender.send(smm);
    }
}
