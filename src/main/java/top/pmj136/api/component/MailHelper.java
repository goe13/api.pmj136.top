package top.pmj136.api.component;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 彭明久
 * @since 2020-11-18
 */
@Component
public class MailHelper {
    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private MailProperties mailProperties;


    /*发送邮件*/
    public void send(String source, String text, String target) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject(source);
        smm.setText(text);
        smm.setFrom(mailProperties.getUsername());
        smm.setTo(target);
        javaMailSender.send(smm);
    }
}
