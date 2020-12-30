package top.pmj136.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@Data
@TableName("jiu_remark_child")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemarkChild implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父评论id
     */
    @JsonProperty("parent_id")
    private Integer parentId;

    /**
     * root评论id
     */
    @NotNull(message = "comment_id cannot be empty")
    @JsonProperty("comment_id")
    private Integer commentId;

    /**
     * 评论者id
     */
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * 回复内容
     */
    @NotNull(message = "回复内容不能为空")
    @NotBlank(message = "回复内容不能为空")
    private String content;

    /**
     * 回复用户id
     */
    @NotNull(message = "arget_id cannot be empty")
    @JsonProperty("target_id")
    private Integer targetId;

    /**
     * 回复时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;


    /*not exist*/
    @TableField(exist = false)
    @JsonProperty("article_id")
    private Integer articleId;

    @TableField(exist = false)
    private Integer action;

    @TableField(exist = false)
    @JsonProperty("target_nick")
    private String targetNick;

    @TableField(exist = false)
    private String nick;

    @TableField(exist = false)
    @JsonProperty("remark_id")
    private Integer remarkId;

    @TableField(exist = false)
    @JsonProperty("avatar_url")
    private String avatarUrl;


}
