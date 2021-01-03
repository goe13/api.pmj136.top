package top.pmj136.api.advice;

import org.springframework.mail.MailSendException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import top.pmj136.api.exception.AppException;
import top.pmj136.api.util.Result;

/**
 * @author 彭明久
 * @since 2020-10-02
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public Result handleAllPointerExp(Exception e) {
        return Result.build(500, e.toString(), null);
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public Result handleSizeExp(MaxUploadSizeExceededException e) {
        return Result.build(500, "图片大小不能大于2M", null);
    }

    @ExceptionHandler({MailSendException.class})
    public Result handleAllPointerExp(MailSendException e) {
        return Result.build(500, "邮件发送失败", null);
    }

    @ExceptionHandler({BindException.class})
    public Result handleBindExp(BindException e) {
        return Result.build(210, e.getBindingResult().getFieldError().getDefaultMessage(), null);
    }

    @ExceptionHandler({AppException.class})
    public Result handleAppExp(AppException e) {
        return Result.build(e.getCode(), e.getMsg(), null);
    }
}
