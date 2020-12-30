package top.pmj136.api.common;

import org.springframework.mail.MailSendException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import top.pmj136.api.util.Result;

/**
 * @author 彭明久
 * @since 2020-10-02
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public Result handleAllPointerExp(Exception e) {
        System.out.println(e);
        return Result.build(500, e.getMessage(), false);
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public Result handleSizeExp(MaxUploadSizeExceededException e) {
        return Result.build(500, "图片大小不能大于2M", false);
    }

    @ExceptionHandler({MailSendException.class})
    public Result handleAllPointerExp(MailSendException e) {
        return Result.build(500, "邮件发送失败", false);
    }

    @ExceptionHandler({BindException.class})
    public Result handleBindExp(BindException e) {
        return Result.build(210, e.getBindingResult().getFieldError().getDefaultMessage(), false);
    }
}
