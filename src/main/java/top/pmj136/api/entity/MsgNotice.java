package top.pmj136.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@Data
@TableName("jiu_msg_notice")
public class MsgNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 1:关注；2:取关
     */
    private Integer action;

    /**
     * 用户id
     */
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * 接收者id
     */
    @JsonProperty("target_id")
    private Integer targetId;


    /**
     * 消息时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /**
     * 是否查看
     */
    @JsonProperty("is_review")
    private Integer isReview;


}
