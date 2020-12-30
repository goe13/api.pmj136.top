package top.pmj136.api.util;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 彭明久
 * @since 2020-09-13
 */
@Getter
@Setter
@AllArgsConstructor
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;


    public static Result resolve() {
        return new Result(200, "success", null);
    }

    public static Result resolve(String msg) {
        return new Result(200, msg, null);
    }

    public static Result resolve(Object data) {
        return new Result(200, "success", data);
    }

    public static Result resolve(String msg, Object data) {
        return new Result(200, msg, data);
    }

    public static Result reject() {
        return new Result(210, "error", null);
    }

    public static Result reject(String msg) {
        return new Result(210, msg, null);
    }

    public static Result reject(Object data) {
        return new Result(210, "error", data);
    }

    public static Result reject(String msg, Object data) {
        return new Result(210, msg, data);
    }

    public static Result build(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
