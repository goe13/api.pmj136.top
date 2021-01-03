package top.pmj136.api.exception;

import lombok.Data;

/**
 * @author 彭明久
 * @since 2021-01-03
 */
@Data
public class AppException extends Exception {
    private Integer code;
    private String msg;

    public AppException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
